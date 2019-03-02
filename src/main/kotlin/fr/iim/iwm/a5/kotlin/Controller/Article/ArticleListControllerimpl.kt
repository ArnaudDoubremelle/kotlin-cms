package fr.iim.iwm.a5.kotlin.Controller.Article

import fr.iim.iwm.a5.kotlin.Model.Model
import fr.iim.iwm.a5.kotlin.Model.SessionProvider
import io.ktor.freemarker.FreeMarkerContent

class ArticleListControllerImpl(private val model: Model) :
    ArticleListController {

    override fun startFM(sessionProvider: SessionProvider): Any {
        val articles = model.getArticleList()
        return FreeMarkerContent("index.ftl", mapOf("articles" to articles, "session" to sessionProvider.getSession()), "e")
    }

}