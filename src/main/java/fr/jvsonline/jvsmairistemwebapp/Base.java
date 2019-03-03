package fr.jvsonline.jvsmairistemwebapp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Template;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import fr.jvsonline.jvsmairistemcli.core.*;
import fr.jvsonline.jvsmairistemcli.omega.*;
import fr.jvsonline.jvsmairistemcli.omega.manager.*;
import fr.jvsonline.jvsmairistemcli.omega.model.*;

/**
 * Basic search
 *
 */
public class Base extends HttpServlet {
  
  /**
   * @var Logger
   */
  protected static final Logger logger = LoggerFactory.getLogger(Base.class);
  
  /**
   * 
   */
  protected static JsonApiWS wsClient = null;
  
  /**
   * 
   */
  protected static Container omegaContainer = null;
  
  /**
   * 
   */
  protected static Configuration templater = null;
  
  /**
   * 
   */
  protected void initJvsMairistemCli() {
    logger.info("----------------------------------------------------------");
    logger.info("JVS-Mairistem Client settings...");
    Settings omegaSettings = Settings.getInstance();
    omegaSettings.setWsEndpoint("https://omegaweb-pp.jvsonline.fr/api/v1");
    omegaSettings.setWsApiId("dfc7258e09f94ba6e495a08c50d6fd00@partner-kis-jolieville");
    omegaSettings.setWsHawkId("partner-kis-jolieville");
    omegaSettings.setWsHawkKey("be58e42b95c303f3a64faf5c70f7d7e7");
    logger.info("   Version  : " + omegaSettings.getVersion());
    logger.info("   Endpoint : " + omegaSettings.getWsEndpoint());
    this.wsClient = new JsonApiWS(omegaSettings);
    this.omegaContainer = new Container();
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
  }
  
  /**
   * 
   */
  public void initTemplater() {
    this.templater = new Configuration(Configuration.VERSION_2_3_27);
    this.templater.setClassForTemplateLoading(Base.class, "/views");
    this.templater.setDefaultEncoding("UTF-8");
    this.templater.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    this.templater.setLogTemplateExceptions(false);
    this.templater.setWrapUncheckedExceptions(true);
  }
  
  /**
   * 
   * @throws ServletException
   */
  public void init() throws ServletException {
    logger.info("----------------------------------------------------------");
    logger.info("init() Method.");
    this.initJvsMairistemCli();
    this.initTemplater();
    logger.info("----------------------------------------------------------");
  }
  
  /**
   * 
   * @param String p_name
   * 
   * @return freemarker.template.Template
   */
  public Template getTemplate(String p_name) {
    logger.info("----------------------------------------------------------");
    logger.info("getTemplate() Method.");
    try {
        return this.templater.getTemplate(p_name + ".ftlh");
    } catch (Exception ex) {
      // @todo
      logger.error(ex.getMessage());
    }
    logger.info("----------------------------------------------------------");
    return null;
  }
  
  /**
   * 
   * @return Map<String, Object>
   */
  public Map<String, Object> getBaseData() {
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("siteName", "Jvs-Mairistem WebApp");
    return templateData;
  }
  
  /**
   * 
   * @param response
   * @param p_message
   */
  public void error404(HttpServletResponse response, String p_message) {
    logger.info("----------------------------------------------------------");
    logger.info("error404() Method.");
    logger.info(p_message);
    try {
      Template template = this.getTemplate("error-404");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      templateData.put("message", p_message);
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      // @todo
      logger.error(ex.getMessage());
    }
    logger.info("----------------------------------------------------------");
  }
  
  /**
   * 
   * @param response
   * @param p_message
   */
  public void error500(HttpServletResponse response, String p_message) {
    logger.info("----------------------------------------------------------");
    logger.info("error500() Method.");
    logger.info(p_message);
    try {
      Template template = this.getTemplate("error-500");
      OutputStreamWriter outputWriter = new OutputStreamWriter(response.getOutputStream());
      Map<String, Object> templateData = this.getBaseData();
      templateData.put("message", p_message);
      template.process(templateData, outputWriter);
      outputWriter.flush();
      outputWriter.close();
    } catch (Exception ex) {
      // @todo
      logger.error(ex.getMessage());
    }
    logger.info("----------------------------------------------------------");
  }
  
  /**
   * 
   */
  public void destroy() {
    logger.info("----------------------------------------------------------");
    logger.info("destroy() Method.");
    logger.info("----------------------------------------------------------");
  }
}