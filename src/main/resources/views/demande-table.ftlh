<#import "index.ftlh" as u>

<@u.page>
    <div class="card>"
      <div class="card-header">
        <h2 class="form-title">Demandes</h2>
      </div>
      <div class="card-body">
        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th>
                Identifiant
              </th>
              <th>
                Type
              </th>
              <th>
                Status
              </th>
              <th>
                Actions
              </th>
            </tr>
          </thead>
          <tbody>
            <#list demandes as dmd>
              <tr>
                <td>
                  <a href="demande?id=${dmd.getId()?long?c}">
                    <span>
                      ${dmd.getId()}
                    </span>
                  </a>
                </td>
                <td>
                  <span>
                    ${dmd.getCode()}
                  </span>
                </td>
                <td>
                  <span>
                    ${dmd.getStatus()}
                  </span>
                </td>
                <td>
                  <div class="btn-group btn-group-sm" role="group" aria-label="Button group with nested dropdown">
                      <button type="button" class="btn btn-light">
                        <a href="demande?id=${dmd.getId()?long?c}">
                          <span>
                             <i class="far fa-edit"></i>
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