<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>JVS-Mairistem WebApp</title>
  </head>
  <body>
    <form class="card" action="point-de-consommation" method="POST">
      <div class="card-header">
        <h2 class="form-title">Détail du point de consommation</h2>
      </div>
      <div class="card-body">
        <div class="form-group">
          <label for="id">Identifiant : </label>
          <input type="text" id="id" name="id" value="${pconso.getId()?long?c}" />
        </div>
        <div class="form-group">
          <label for="numero">Numéro : </label>
          <input type="text" id="numero" name="numero" value="${pconso.getNumero()}" />
        </div>
      </div>
      <div class="card-footer">
        <input type="submit" value="Valider" />
      </div>
    </form>
  </body>
</html>