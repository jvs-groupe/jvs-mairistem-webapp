<#import "index.ftlh" as u>

<@u.page>
    <form class="card" action="point-de-consommation-list" method="POST">
      <div class="card-header">
        <h2 class="form-title">Recherche de points de consommation</h2>
      </div>
      <div class="card-body">
        <div class="form-group row">
          <label class="col-3 col-form-label" for="firstname">Pr&eacute;nom de l'occupant : </label>
          <div class="col-9">
            <input class="form-control" type="text" id="firstname" name="firstname" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-3 col-form-label" for="lastname">Nom de l'occupant : </label>
          <div class="col-9">
            <input class="form-control" type="text" id="lastname" name="lastname" />
          </div>
        </div>
      </div>
      <div class="card-footer text-right">
        <input class="btn btn-primary" type="submit" value="Rechercher" />
      </div>
    </form>
</@u.page>