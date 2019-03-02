<#include "static/header.ftl">

<#list articles as article>
<div class="col-lg-12">
    <h4>${article.title}</h4>
    <a href="/article/${article.id}">Lire l'article</a>
</div>
</#list>


<#include "static/footer.ftl">