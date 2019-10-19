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
 * Gestion de la liste des demandes
 *
 */
public class DemandeList extends Base {

  /**
   * Fenêtre de recherche des demandes
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
      Template template = this.getTemplate("demande-search");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      templateData.put("codes", DemandeCodeEnum.values());
      templateData.put("status", DemandeStatusEnum.values());
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
      return;
    }
  }

  /**
   * Recherche des demandes
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
    String code = request.getParameter("code");
    String status = request.getParameter("status");
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    // Appel su service web
    DemandeManager myManager = null;
    try {
      // Instance du manager pour la recherche,
      // Manager qui reçoit le client ws en paramètre
      myManager = new DemandeManager(this.wsClient);
      // Initialisation puis ajout des paramètres de recherche
      myManager.flushRequestParameters();
      if (code != "") {
        myManager.addRequestParameter("code", code);
      }
      if (status != "") {
        myManager.addRequestParameter("status", status);
      }
      myManager.setPage(1);
      myManager.setPageLimit(10);
      // ON lance la recherche
      List<DemandeModel> myList = myManager.find();
      // Interprétation du résultat
      Template template = this.getTemplate("demande-table");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      templateData.put("demandes", myList);
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
      return;
    }
  }
}
