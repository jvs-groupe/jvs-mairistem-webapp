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
 * Gestion d'un point de consommation
 *
 */
public class PointConsommation extends Base {

  /**
   * Get standard d'un point de consommation par Id
   * 
   * @return void
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Réponse par défaut
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String pconsoId = request.getParameter("id");
    try {
      PointDeConsommationManager pconsoManager = null;
      pconsoManager = new PointDeConsommationManager(this.wsClient);
      pconsoManager.flushRequestParameters();
      PointDeConsommationModel myPConso = pconsoManager.getById(Integer.valueOf(pconsoId));
      if (myPConso != null) {
        Template template = this.getTemplate("pconso-form");
        OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
        Map<String, Object> templateData = this.getBaseData();
        templateData.put("pconso", myPConso);
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
  }
}
