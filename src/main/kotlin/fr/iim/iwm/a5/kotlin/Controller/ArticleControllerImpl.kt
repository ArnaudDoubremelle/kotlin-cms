package fr.iim.iwm.a5.kotlin.Controller

import fr.iim.iwm.a5.kotlin.Model.Model
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) :
    ArticleController {

    override fun startFM(id: Int): Any {
        val article = model.getArticle(id)
        val comments =  model.getCommentsByArticle(id)
        if (article !== null) {
            return FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments), "e")

        }
        return HttpStatusCode.NotFound
    }

}