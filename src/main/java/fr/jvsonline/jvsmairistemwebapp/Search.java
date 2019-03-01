package fr.jvsonline.jvsmairistemwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.jvsonline.jvsmairistemcli.core.*;
import fr.jvsonline.jvsmairistemcli.omega.Container;
import fr.jvsonline.jvsmairistemcli.omega.manager.*;
import fr.jvsonline.jvsmairistemcli.omega.model.*;

/**
 * Basic search
 *
 */
public class Search extends HttpServlet {
  
  /**
   * @var Logger
   */
  protected static final Logger logger = LoggerFactory.getLogger(Search.class);
  
  /**
   * 
   */
  private static final long serialVersionUID = -1641096228274971485L;
  
  /**
   * 
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    
    // set response headers
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    
    // create HTML form
    PrintWriter writer = response.getWriter();
    writer
      .append("<!DOCTYPE html>\r\n")
      .append("<html>\r\n")
      .append("  <head>\r\n")
      .append("    <title>Formulaire de recherche de points de consommation</title>\r\n")
      .append("  </head>\r\n")
      .append("  <body>\r\n")
      .append("    <form action=\"search\" method=\"POST\">\r\n")
      .append("      Occupant à rechercher : \r\n")
      .append("      <input type=\"text\" name=\"search\" />\r\n")
      .append("      <input type=\"submit\" value=\"Rechercher\" />\r\n")
      .append("    </form>\r\n")
      .append("  </body>\r\n")
      .append("</html>\r\n")
    ;
  }
  
  /**
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    
    String search = request.getParameter("search");
    
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    
    logger.info("----------------------------------------------------------");
    logger.info("JVS-Mairistem Client settings...");
    Settings omegaSettings = Settings.getInstance();
    omegaSettings.setWsEndpoint("https://omegaweb-pp.jvsonline.fr/api/v1");
    omegaSettings.setWsApiId("dfc7258e09f94ba6e495a08c50d6fd00@partner-kis-jolieville");
    omegaSettings.setWsHawkId("partner-kis-jolieville");
    omegaSettings.setWsHawkKey("be58e42b95c303f3a64faf5c70f7d7e7");
    logger.info("   Version  : " + omegaSettings.getVersion());
    logger.info("   Endpoint : " + omegaSettings.getWsEndpoint());
    logger.info("----------------------------------------------------------");
    JsonApiWS wsClient = new JsonApiWS(omegaSettings);
    Container omegaContainer = new Container();
    
    // create HTML response
    PrintWriter writer = response.getWriter();
    writer
      .append("<!DOCTYPE html>\r\n")
      .append("<html>\r\n")
      .append("  <head>\r\n")
      .append("    <title>Résultat de recherche de points de consommation</title>\r\n")
      .append("  </head>\r\n")
      .append("  <body>\r\n")
    ;
    
    
    logger.info("----------------------------------------------------------");
    logger.info("   Récupération des codifications...");
    EnumerationManager enumManager = null;
    enumManager = new EnumerationManager(wsClient);
    List<EnumerationModel> myListE = enumManager.find();
    if (myListE == null) {
      logger.info("Empty result...");
    } else {
      for (EnumerationModel item : myListE) {
        logger.info("Enumération " + item.getNom());
      }
    }
    omegaContainer.setEnums(myListE);
    logger.info("----------------------------------------------------------");
    
    
    logger.info("----------------------------------------------------------");
    logger.info("   Liste des points de consommation...");
    PointDeConsommationManager pconsoManager = null;
    pconsoManager = new PointDeConsommationManager(wsClient);
    pconsoManager.flushRequestParameters();
    pconsoManager.addRequestParameter("contratActif.occupant.prenom", search);
    List<PointDeConsommationModel> myListN = pconsoManager.find();
    if (myListN == null) {
      logger.info("Empty result...");
      writer
        .append("<h2>Aucun résultat !</h2>\r\n")
      ;
    } else {
      writer
        .append("<table>\r\n")
        .append("  <thead>\r\n")
        .append("    <tr><th>Numéro</th></tr>\r\n")
        .append("  </thead>\r\n")
        .append("  <tbody>\r\n")
      ;
      for (PointDeConsommationModel item : myListN) {
        String numero = item.getNumero();
        CompteurModel monCompteur = item.getCompteur();
        String numSerie = "";
        if (monCompteur != null) {
          numSerie = monCompteur.getNumeroSerie();
        }
        logger.info("Pconso n° " + item.getNumero() + " : " + numero + " [" + numSerie + "]");
        ContratModel contratActif = item.getContratActif();
        if (contratActif != null) {
          logger.info("    * " + contratActif.getOccupant());
        }
        logger.info("    * " + item.toAdresse());
        writer
          .append("    <tr>\r\n")
          .append("      <td>\r\n")
        ;
        writer
          .append(item.getNumero())
        ;
        writer
          .append("      </td>\r\n")
          .append("    </tr>\r\n")
        ;
      }
      writer
        .append("  </tbody>\r\n")
        .append("</table>\r\n")
      ;
    }
    logger.info("----------------------------------------------------------");
    
    writer
      .append("  </body>\r\n")
      .append("</html>\r\n")
    ;
  }
}
