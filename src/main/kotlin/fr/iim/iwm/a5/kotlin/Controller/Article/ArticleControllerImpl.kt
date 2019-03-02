package fr.iim.iwm.a5.kotlin.Controller.Article

import fr.iim.iwm.a5.kotlin.Model.Article
import fr.iim.iwm.a5.kotlin.Model.Model
import fr.iim.iwm.a5.kotlin.Model.SessionProvider
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) :
    ArticleController {

    override fun startFM(id: Int, sessionProvider: SessionProvider): Any {
        val article = model.getArticle(id)
        val comments =  model.getCommentsByArticle(id)
        if (article !== null) {
            return FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments, "session" to sessionProvider.getSession()), "e")

        }
        return HttpStatusCode.NotFound
    }

    override fun createArticle (title: String, text: String) {
        model.createArticle(Article(0, title, text))
    }

    override fun deleteArticle (id: Int) {
        model.deleteArticle(id)
    }

}