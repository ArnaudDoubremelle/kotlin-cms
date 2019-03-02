package fr.iim.iwm.a5.kotlin

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.*
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class App

data class IndexData(val articles: List<Article>)

fun main() {

    val model = MysqlModel()
    val articleListController = ArticleListController(model)
    val articleController = ArticleController(model)

    embeddedServer(Netty, 8080) {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        routing {
            get("/") {
                val content = articleListController.startFM()
                call.respond(content)
            }

            get("/article/{id}") {
                var id = call.parameters["id"]!!.toInt()
                val content = articleController.startFM(id)
                call.respond(content)
            }
        }
    }.start(true)
}