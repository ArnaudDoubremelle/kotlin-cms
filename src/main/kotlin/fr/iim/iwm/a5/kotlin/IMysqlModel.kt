package fr.iim.iwm.a5.kotlin

interface IMysqlModel {
    fun getArticlesList(): List<Article>
    fun getArticle(id: Int): Article?
}