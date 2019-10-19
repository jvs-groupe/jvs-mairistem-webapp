package fr.jvsonline.jvsmairistemwebapp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Template;

import fr.jvsonline.jvsmairistemcli.core.*;
import fr.jvsonline.jvsmairistemcli.omega.Container;
import fr.jvsonline.jvsmairistemcli.omega.manager.*;
import fr.jvsonline.jvsmairistemcli.omega.model.*;

/**
 * Factures
 *
 */
public class Facture extends Base {

  /**
   * Get classique d'un point de consommation avec son identifiant
   * 
   * @return void
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameterMap().containsKey("id")) {
      String pconsoId = request.getParameter("id");
      try {
        PointDeConsommationManager pconsoManager = null;
        pconsoManager = new PointDeConsommationManager(this.wsClient);
        pconsoManager.flushRequestParameters();
        PointDeConsommationModel myPConso = pconsoManager.getById(Integer.valueOf(pconsoId));
        if (myPConso != null) {
          Template template = this.getTemplate("facture-form");
          OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
          Map<String, Object> templateData = this.getBaseData();
          templateData.put("pconso", myPConso);
          templateData.put("redevable", myPConso.getContratActif().getRedevable());
          templateData.put("articles", this.omegaContainer.getArticles());
          template.process(templateData, outputWriter);
          outputWriter.flush();
          outputWriter.close();
        } else {
          this.error404(response, pconsoId);
          return;
        }
      } catch (Exception ex) {
        this.error500(response, ex.getMessage());
        return;
      }
    } else {
      this.error500(response, "Id obligatoire !");
      return;
    }
  }
  
  /**
   * Recherche de points de consommation
   * 
   * @param request  Requête
   * @param response Réponse
   *
   * @throws ServletException
   * @throws IOException
   * 
   * @return void
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Paramètres de recherche
    String idPconso = request.getParameter("idpconso");
    String idRede = request.getParameter("idrede");
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    // Appel su service web
    PointDeConsommationManager pconsoManager = null;
    try {
      pconsoManager = new PointDeConsommationManager(this.wsClient);
      pconsoManager.flushRequestParameters();
      PointDeConsommationModel myPConso = pconsoManager.getById(Integer.valueOf(idPconso));
      DemandeManager reqManager = new DemandeManager(wsClient);
      // Ajouter une facture externe
      Date aujourdhui = new Date(System.currentTimeMillis());
      FactureExterneModel uneFacture = new FactureExterneModel();
      uneFacture.setDateFacture(aujourdhui);
      uneFacture.setPointDeConsommation(myPConso);
      uneFacture.setRedevable(myPConso.getContratActif().getRedevable());
      uneFacture.setOrganismeFactureur(omegaContainer.getOrganismeFactureur());
      // Une ligne
      Integer eId = 1;
      while (eId < 2) {
        String codArt = request.getParameter("article" + eId);
        String qteArt = request.getParameter("qte" + eId);
        if (codArt != "") {
          LigneFactureExterneModel uneLigne1 = new LigneFactureExterneModel();
          uneLigne1.setId((-1 * eId));
          uneLigne1.setCodeArticle(codArt);
          uneLigne1.setQuantite(Float.valueOf(qteArt));
          uneFacture.addLigneFactureExterne(uneLigne1);
        }
        eId++;
      }
      DemandeModel myDmd = reqManager.sendFactureExterne(uneFacture);
      response.sendRedirect(request.getContextPath() + "/demande?id=" + myDmd.getId());
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
      return;
    }
  }
}
