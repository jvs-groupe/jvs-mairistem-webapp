<#import "index.ftlh" as u>

<@u.page>
    <form class="card" action="demande" method="POST">
      <div class="card-header">
        <h2 class="form-title">Détail de la demande</h2>
      </div>
      <div class="card-body">
        <div class="form-group row">
          <label class="col-3 col-form-label" for="id">Identifiant : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="id" name="id" value="${demande.getId()?long?c}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="code">Type : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="code" name="code" value="${demande.getCode().getLabel()}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="status">Status : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="status" name="status" value="${demande.getStatus().getLabel()}" />
          </div>
        </div>
      </div>
      <div class="card-footer text-right">
        <input class="btn btn-primary" type="button" value="Retour" />
      </div>
    </form>
</@u.page>