package fr.jvsonline.jvsmairistemwebapp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Gestion de la liste des points de consommation
 *
 */
public class PointConsommationList extends Base {

  /**
   * Fenêtre de recherche des points de consommation
   * 
   * @return void
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    // Page basique depuis le template
    try {
      Template template = this.getTemplate("pconso-search");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
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
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    // Appel su service web
    PointDeConsommationManager pconsoManager = null;
    try {
      // Instance du manager pour la recherche,
      // Manager qui reçoit le client ws en paramètre
      pconsoManager = new PointDeConsommationManager(this.wsClient);
      // Initialisation puis ajout des paramètres de recherche
      pconsoManager.flushRequestParameters();
      if (firstname != "") {
        pconsoManager.addRequestParameter("contratActif.occupant.prenom", firstname);
      }
      if (lastname != "") {
        pconsoManager.addRequestParameter("contratActif.occupant.nom", lastname);
      }
      pconsoManager.setPage(1);
      pconsoManager.setPageLimit(10);
      // ON lance la recherche
      List<PointDeConsommationModel> myListN = pconsoManager.findBasic();
      // Interprétation du résultat
      Template template = this.getTemplate("pconso-table");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      templateData.put("pconsos", myListN);
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
      return;
    }
  }
}
