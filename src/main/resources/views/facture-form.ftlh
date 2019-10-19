<#import "index.ftlh" as u>

<@u.page>
    <form class="card" action="point-de-consommation-ajout-facture" method="POST">
      <div class="card-header">
        <h2 class="form-title">Ajout d'une facture</h2>
      </div>
      <div class="card-body">
        <h3 class="form-title">Point de consommation</h3>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="id">Identifiant : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="idpconso" name="idpconso" value="${pconso.getId()?long?c}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="numero">Num&eacute;ro : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="numero" name="numero" value="${pconso.getNumero()}" />
          </div>
        </div>
        <h3 class="form-title">Redevable</h3>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="idrede">Identifiant : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="idrede" name="idrede" value="${redevable.getId()}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="nomrede">Nom : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="nomrede" name="nomrede" value="${redevable.getNom()}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="prenrede">Prénom : </label>
          <div class="col-9">
            <input class="form-control" type="text" readonly id="prenrede" name="prenrede" value="${redevable.getPrenom()}" />
          </div>
        </div>
        <h3 class="form-title">Facturation</h3>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="prenrede">Article 1 : </label>
          <div class="col-3">
            <select class="form-control" id="article1" name="article1" value="">
              <option></option>
              <#list articles as article>
                <option value="${article.getCode()}">${article.getLibelle()}</option>
              </#list>
            </select>
          </div>
          <div class="col-3">
            <input class="form-control" type="text" id="qte1" name="qte1" value="0" />
          </div>
        </div>
      </div>
      <div class="card-footer text-right">
        <input class="btn btn-success" type="submit" value="Valider" />
        <input class="btn btn-primary" type="button" value="Retour" />
      </div>
    </form>
</@u.page>