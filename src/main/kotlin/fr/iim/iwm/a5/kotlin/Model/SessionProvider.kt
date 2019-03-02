package fr.iim.iwm.a5.kotlin.Model

interface SessionProvider {
    fun getSession(): UserSession?
}