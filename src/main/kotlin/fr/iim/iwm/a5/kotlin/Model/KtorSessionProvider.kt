package fr.iim.iwm.a5.kotlin.Model

import io.ktor.application.ApplicationCall
import io.ktor.sessions.get
import io.ktor.sessions.sessions

class KtorSessionProvider(private val call: ApplicationCall): SessionProvider {
    override fun getSession() = call.sessions.get<UserSession>()
}