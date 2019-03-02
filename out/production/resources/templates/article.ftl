
<#include "static/header.ftl">

<h1>${article.title}</h1>
<p>${article.text}</p>

<hr>

<div class="row">
    <div class="col-md-6">
        <form action="/comment/create" method="post">
            <input type="hidden" name="idArticle" value="${article.id}">
            <div class="form-group">
                <label for="comment">Ecrire un commentaire</label>
                <textarea id="comment" class="form-control" name="text" rows="5" required ></textarea>
            </div>
            <input type="submit" value="Commenter" class="btn btn-primary">
        </form>
    </div>
    <div class="col-md-6">
        <h2>Tous les commentaires</h2>
        <#list comments?reverse as comment>
            <p>
                ${comment.text}
            </p>
            <a href="/comment/delete/${comment.id}/${comment.idArticle}">Supprimer</a>
            <hr>
        </#list>
    </div>
</div>

<#include "static/footer.ftl">