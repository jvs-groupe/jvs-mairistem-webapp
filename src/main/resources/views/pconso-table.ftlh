<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>JVS-Mairistem WebApp</title>
  </head>
  <body>
    <div class="card>"
      <div class="card-header">
        <h2 class="form-title">Points de consommation</h2>
      </div>
      <div class="card-body">
        <table>
          <thead>
            <tr>
              <th>
                Identifiant
              </th>
              <th>
                Numéro
              </th>
              <th>
                Adresse
              </th>
              <th>
                Occupant
              </th>
            </tr>
          </thead>
          <tbody>
            <#list pconsos as pc>
              <tr>
                <td>
                  <a href="point-de-consommation?id=${pc.getId()?long?c}">
                    <span>
                      ${pc.getId()}
                    </span>
                  </a>
                </td>
                <td>
                  <span>
                    ${pc.getNumero()}
                  </span>
                </td>
                <td>
                  <span>
                    ${pc.toAdresse()}
                  </span>
                </td>
                <td>
                  <span>
                    ${pc.getContratActif().getOccupant().getNomComplet()}
                  </span>
                </td>
              </tr>
            </#list>
          </tbody>
        </table>
      </div>
  </body>
</html>