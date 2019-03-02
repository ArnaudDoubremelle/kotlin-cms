package fr.iim.iwm.a5.kotlin.Controller.User

import fr.iim.iwm.a5.kotlin.Model.SessionProvider
import io.ktor.application.ApplicationCall


interface UserController {
    fun login(sessionProvider: SessionProvider): Any
    fun loginAction(username: String?, password: String?, context: ApplicationCall): String
    fun disconnectAction(context: ApplicationCall): String
}