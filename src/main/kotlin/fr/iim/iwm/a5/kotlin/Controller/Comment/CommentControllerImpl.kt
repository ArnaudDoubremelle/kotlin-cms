package fr.iim.iwm.a5.kotlin.Controller.Comment

import fr.iim.iwm.a5.kotlin.Model.Comment
import fr.iim.iwm.a5.kotlin.Model.Model

class CommentControllerImpl(private val model: Model) : CommentController {

    override fun createComment (article: Int, text: String) {
        model.createComment(Comment(0, article, text))
    }

    override fun deleteComment (id: Int) {
        model.deleteComment(id)
    }

}