package fr.iim.iwm.a5.kotlin.Controller.Article

import fr.iim.iwm.a5.kotlin.Model.SessionProvider

interface ArticleListController {
    fun startFM(sessionProvider: SessionProvider): Any
}