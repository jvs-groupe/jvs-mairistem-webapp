# jvs-mairistem-webapp

Exemple d'intégration basique du client java jvs-mairistem via freeMarker & servLet.

## Installation

Le plus simple est d'utiliser le conteneur. Il faut donc récupérer le projet en local, via un git clone par exemple et lancer le démarrage du conteneur

```
  // Se placer dans son répertoire de travail
  cd $myHome
  // Cloner le projet
  git clone https://github.com/jvs-groupe/jvs-mairistem-webapp.git
  // Se positionner dans le projet à la racine
  cd jvs-mairistem-webapp
  // Lancer le conteneur
  docker-compose up
```

Il suffit ensuite de se rendre à l'url : http://localhost:8080. Le port par défaut peut être changé dans le fichier docker-compose.yml


### Commandes

Pour compiler le projet manuellement, cepuis le conteneur dans le homedir du projet, à l'emplacement du fichier pom.xml.

```
    // Installer le client en local
    cd /opt/java/jvs-mairistem-webapp/lib
    mvn install:install-file -Dfile=JvsMairistemCli-1.0.1.jar -DpomFile=JvsMairistemCli-1.0.1.pom.xml
    // Se placer dans le bon répertoire
    cd /opt/java/jvs-mairistem-webapp
    // Package
    mvn compile war:war
    // Ajouter l'application dans Tomcat (accès via l'utilisateur admin/admin)
```

### Structure

```
   |---- opt
   |      |---- apache-tomcat9
   |      |      |---- logs
   |      |      |      |---- localhost.*.log
   |      |      |      |---- JvsMairistemWebApp.log
   |      |      |---- webapps
   |      |      |      |---- JvsMairistemWebApp
   |      |      |      |---- JvsMairistemWebApp.war     -> lien vers archive
   |      |---- java
   |      |      |---- jvs-mairistem-webapp              -> clone repository
   |      |      |      |---- target   
   |      |      |              |---- JvsMairistemWebApp.war     -> archive war
   |
```
