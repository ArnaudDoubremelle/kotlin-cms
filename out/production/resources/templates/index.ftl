<#include "static/header.ftl">

<#if session??>
    <div class="col-md-12">
        <h2>Ajouter un article</h2>
        <form action="/article/create" method="post">
            <div class="form-group">
                <label for="title">Titre</label>
                <input id="title" type="text" name="title" required>
            </div>
            <div class="form-group">
                <label for="text">Contenu</label>
                <textarea id="text" class="form-control" name="text" rows="6" required ></textarea>
            </div>
            <input type="submit" value="Ajouter un article" class="btn btn-primary">
        </form>
    <hr>
    </div>
</#if>

<div class="col-md-12">
    <div class="row">
        <h2>Tous les articles</h2>
        <div class="col-md-12">
            <#list articles?reverse as article>
                <div class="col-md-12">
                    <h4>${article.title}</h4>
                    <a href="/article/${article.id}">Lire l'article</a>
                    <#if session??>
                        <a href="/article/delete/${article.id}" class="btn btn-danger">Supprimer</a>
                    </#if>
                </div>
            </#list>
        </div>
    </div>
</div>

<#include "static/footer.ftl">