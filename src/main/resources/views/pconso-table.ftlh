<#import "index.ftlh" as u>

<@u.page>
    <div class="card>"
      <div class="card-header">
        <h2 class="form-title">Points de consommation</h2>
      </div>
      <div class="card-body">
        <table class="table">
          <thead class="thead-dark">
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
              <th>
                Contrat
              </th>
              <th>
                Actions
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
                <td>
                  <span>
                    ${pc.getContratActif().getNumero()}
                  </span>
                </td>
                <td>
                  <div class="btn-group btn-group-sm" role="group" aria-label="Button group with nested dropdown">
                      <button type="button" class="btn btn-light">
                        <a href="point-de-consommation?id=${pc.getId()?long?c}">
                          <span>
                             <i class="far fa-edit"></i>
                          </span>
                        </a>
                      </button>
                      <button type="button" class="btn btn-light">
                        <a href="point-de-consommation-ajout-facture?id=${pc.getId()?long?c}">
                          <span>
                              <i class="fas fa-file-invoice-dollar"></i>
                          </span>
                        </a>
                      </button>
                  </div>
                </td>
              </tr>
            </#list>
          </tbody>
        </table>
      </div>
</@u.page>