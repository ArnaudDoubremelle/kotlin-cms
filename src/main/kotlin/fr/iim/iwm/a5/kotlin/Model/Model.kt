package fr.iim.iwm.a5.kotlin.Model

import fr.iim.iwm.a5.kotlin.Model.Article

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun getCommentsByArticle(article: Int): List<Comment>
    fun createComment(comment: Comment)
    fun deleteComment(id: Int)
    fun getUser(login: String?): User?
}