<#import "index.ftlh" as u>

<@u.page>
    <form class="card" action="demande-list" method="POST">
      <div class="card-header">
        <h2 class="form-title">Recherche de demandes</h2>
      </div>
      <div class="card-body">
        <div class="form-group row">
          <label class="col-3 col-form-label" for="code">Type de demande : </label>
          <div class="col-9">
            <select class="form-control" id="code" name="code">
                <option></option>
                <#list codes as code>
                  <option value="${code}">${code.getLabel()}</option>
                </#list>
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="code">Status de demande : </label>
          <div class="col-9">
            <select class="form-control" id="status" name="status">
                <option></option>
                <#list status as sta>
                  <option value="${sta}">${sta.getLabel()}</option>
                </#list>
            </select>
          </div>
        </div>
      </div>
      <div class="card-footer text-right">
        <input class="btn btn-primary" type="submit" value="Rechercher" />
      </div>
    </form>
</@u.page>