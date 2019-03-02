
<#include "static/header.ftl">

<h1>Connexion</h1>

<form action="/login" method="post">
    <div class="form-group row">
        <label for="username" class="col-sm-4 col-form-label text-md-right">Username</label>
        <div class="col-md-6">
            <input id="username" type="text" class="form-control" name="username" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
        <div class="col-md-6">
            <input id="password" type="password" class="form-control" name="password" required>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
</form>

<#include "static/footer.ftl">