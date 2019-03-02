package fr.iim.iwm.a5.kotlin

import fr.iim.iwm.a5.kotlin.Controller.ArticleControllerImpl
import fr.iim.iwm.a5.kotlin.Controller.ArticleListController
import fr.iim.iwm.a5.kotlin.Controller.ArticleListControllerImpl
import fr.iim.iwm.a5.kotlin.Controller.CommentControllerImpl
import fr.iim.iwm.a5.kotlin.Model.MysqlModel
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.freemarker.FreeMarker
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class App

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleControllerImpl,
    commentController: CommentControllerImpl
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    routing {
        get("/") {
            val content = articleListController.startFM()
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id)
            call.respond(content)
        }

        post("/comment/create") {
            val params = call.receive<Parameters>()
            val idArticle = params["idArticle"]!!.toInt()
            val text = params["text"]!!
            commentController.createComment(idArticle, text)
            call.respondRedirect("/article/$idArticle")
        }

        get("/comment/delete/{id}/{idArticle}") {
            val id = call.parameters["id"]!!.toInt()
            val idArticle = call.parameters["idArticle"]!!.toInt()
            commentController.deleteComment(id)
            call.respondRedirect("/article/$idArticle")
        }

    }
}

fun main() {
    val model = MysqlModel("jdbc:mysql://localhost:3306/cms", "root", "root")

    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val commentController = CommentControllerImpl(model)

    embeddedServer(Netty, 8080) {
        cmsApp(articleListController, articleController, commentController)
    }.start(true)
}