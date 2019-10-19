<#import "index.ftlh" as u>

<@u.page>
    <form class="card" action="point-de-consommation" method="POST">
      <div class="card-header">
        <h2 class="form-title">Détail du point de consommation</h2>
      </div>
      <div class="card-body">
        <div class="form-group row">
          <label class="col-3 col-form-label" for="id">Identifiant : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="id" name="id" value="${pconso.getId()?long?c}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="numero">Num&eacute;ro : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="numero" name="numero" value="${pconso.getNumero()}" />
          </div>
        </div>
      </div>
      <div class="card-footer text-right">
        <input class="btn btn-primary" type="button" value="Retour" />
      </div>
    </form>
</@u.page>