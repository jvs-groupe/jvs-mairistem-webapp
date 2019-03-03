<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>JVS-Mairistem WebApp</title>
  </head>
  <body>
    <h1>Bienvenue !</h1>
    <hr />
    <h2>Heure : <%= LocalDateTime.now() %></h2>
    <hr />
    <h2>Menu :</h2>
    <nav class="navbar">
      <ul>
        <li>
          <a href="point-de-consommation">Recherche de points de consommation</a>
        </li>
      </ul>
    </nav>
    <hr />
  </body>
</html>