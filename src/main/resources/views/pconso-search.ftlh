<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>JVS-Mairistem WebApp</title>
  </head>
  <body>
    <form class="card" action="point-de-consommation" method="POST">
      <div class="card-header">
        <h2 class="form-title">Recherche de points de consommation</h2>
      </div>
      <div class="card-body">
        <div class="form-group">
          <label for="firstname">Prénom de l'occupant : </label>
          <input type="text" id="firstname" name="firstname" />
        </div>
        <div class="form-group">
          <label for="lastname">Nom de l'occupant : </label>
          <input type="text" id="lastname" name="lastname" />
        </div>
      </div>
      <div class="card-footer">
        <input type="submit" value="Rechercher" />
      </div>
    </form>
  </body>
</html>