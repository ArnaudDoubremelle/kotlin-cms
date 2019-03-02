package fr.iim.iwm.a5.kotlin.Controller.User

import fr.iim.iwm.a5.kotlin.Model.Model
import fr.iim.iwm.a5.kotlin.Model.SessionProvider
import fr.iim.iwm.a5.kotlin.Model.UserSession
import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.sessions
import org.mindrot.jbcrypt.BCrypt

class UserControllerImpl(private val model: Model) : UserController {

    override fun login(sessionProvider: SessionProvider): Any {
        return FreeMarkerContent("login.ftl", mapOf("session" to sessionProvider.getSession()), "e")
    }

    override fun loginAction(login: String?, password: String?, context: ApplicationCall): String {
        val user = model.getUser(login)
        if (user != null) {

            if (BCrypt.checkpw(password, user.password)) {
                val userSession = UserSession(user.username, user.id)
                context.sessions.set("user", userSession)
                return "/"
            }

        }
        return "/login"
    }

}