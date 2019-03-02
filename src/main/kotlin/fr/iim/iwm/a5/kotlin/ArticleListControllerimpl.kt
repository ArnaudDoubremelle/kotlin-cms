package fr.iim.iwm.a5.kotlin

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleListControllerImpl(private val model: Model) : ArticleListController {

    override fun startFM(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e")

        }
        return HttpStatusCode.NotFound
    }

}