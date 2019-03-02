package fr.iim.iwm.a5.kotlin.Controller

interface CommentController {
    fun createComment (article: Int, text: String)
    fun deleteComment (id: Int)
}