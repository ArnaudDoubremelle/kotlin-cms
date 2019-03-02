package fr.iim.iwm.a5.kotlin.Controller

import fr.iim.iwm.a5.kotlin.Model.Model
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleListControllerImpl(private val model: Model) :
    ArticleListController {

    override fun startFM(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e")

        }
        return HttpStatusCode.NotFound
    }

}