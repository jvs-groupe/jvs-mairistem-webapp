# jvs-mairistem-webapp

Exemple d'intégration du client java

## Installation

### Commandes

Depuis le conteneur dans le homedir du projet, à l'emplacement du fichier pom.xml

```
    // Installer le client en local
    cd /opt/java/jvs-mairistem-webapp/lib
    mvn install:install-file -Dfile=JvsMairistemCli-1.0.1.jar -DpomFile=JvsMairistemCli-1.0.1.pom.xml
    // Se placer dans le bon répertoire
    cd /opt/java/jvs-mairistem-webapp
    // Package
    mvn compile war:war
    // Ajouter l'application dans Tomcat
```

