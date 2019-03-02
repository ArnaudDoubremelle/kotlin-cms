package fr.iim.iwm.a5.kotlin.Controller.Article

import fr.iim.iwm.a5.kotlin.Model.SessionProvider

interface ArticleController {
    fun startFM(id: Int, sessionProvider: SessionProvider): Any
}