package fr.iim.iwm.a5.kotlin

import fr.iim.iwm.a5.kotlin.Controller.Article.ArticleControllerImpl
import fr.iim.iwm.a5.kotlin.Controller.Article.ArticleListController
import fr.iim.iwm.a5.kotlin.Controller.Article.ArticleListControllerImpl
import fr.iim.iwm.a5.kotlin.Controller.Comment.CommentControllerImpl
import fr.iim.iwm.a5.kotlin.Controller.User.UserControllerImpl
import fr.iim.iwm.a5.kotlin.Model.KtorSessionProvider
import fr.iim.iwm.a5.kotlin.Model.MysqlModel
import fr.iim.iwm.a5.kotlin.Model.UserSession
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
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import org.mindrot.jbcrypt.BCrypt

class App

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleControllerImpl,
    commentController: CommentControllerImpl,
    userController: UserControllerImpl
) {
    install(Sessions) {
        cookie<UserSession>("user") {
            cookie.path = "/"
        }
    }

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    routing {
        get("/") {
            val articles = articleListController.startFM()
            call.respond(articles)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val article = articleController.startFM(id)
            call.respond(article)
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

        get("/login") {
            call.respond(userController.login(KtorSessionProvider(call)))
        }

        post("/login") {
            val params = call.receive<Parameters>()
            val username = params["username"]
            val password = params["password"]
            val content = userController.loginAction(username, password, context)
            call.respondRedirect(content)
        }

    }
}

fun main() {
    val model = MysqlModel("jdbc:mysql://localhost:3306/cms", "root", "root")

    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val commentController = CommentControllerImpl(model)
    val userController = UserControllerImpl(model)

    embeddedServer(Netty, 8080) {
        cmsApp(articleListController, articleController, commentController, userController)
    }.start(true)
}