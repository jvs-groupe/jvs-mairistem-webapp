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
 * Gestion d'une demande
 *
 */
public class Demande extends Base {

  /**
   * Get standard d'un demande par ID
   * 
   * @return void
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String myId = request.getParameter("id");
    try {
      DemandeManager myManager = null;
      myManager = new DemandeManager(this.wsClient);
      myManager.flushRequestParameters();
      DemandeModel myModel = myManager.getById(Integer.valueOf(myId));
      if (myModel != null) {
        Template template = this.getTemplate("demande-form");
        OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
        Map<String, Object> templateData = this.getBaseData();
        templateData.put("demande", myModel);
        template.process(templateData, outputWriter);
        outputWriter.flush();
        outputWriter.close();
      } else {
        this.error404(response, myId);
        return;
      }
    } catch (Exception ex) {
      this.error500(response, ex.getMessage());
      return;
    }
  }
}
