package fr.iim.iwm.a5.kotlin

import io.ktor.freemarker.FreeMarkerContent

class ArticleListController(private val model: MysqlModel) {
    fun startFM(): FreeMarkerContent {
        val articles = model.getArticlesList()
        return  FreeMarkerContent("index.ftl", IndexData(articles))
    }
}