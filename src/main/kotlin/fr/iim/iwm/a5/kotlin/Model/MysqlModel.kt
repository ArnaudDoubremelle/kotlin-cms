package fr.iim.iwm.a5.kotlin.Model

import fr.iim.iwm.a5.kotlin.ConnectionPool

class MysqlModel(val url: String, val user: String?, val password: String?): Model {

    val connectionPool = ConnectionPool(url, user, password)

    override fun getArticleList(): List<Article> {
        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            val stmt = connection.prepareStatement("SELECT * FROM articles")
            val results = stmt.executeQuery()

            while (results.next()) {
                articles.add(Article(results.getInt("id"), results.getString("title")))
            }
        }
        return articles
    }

    override fun getArticle(id: Int): Article? {
        connectionPool.use { connection ->
            val stmt = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")
            stmt.setInt(1, id)
            val results = stmt.executeQuery()
            val found = results.next()
            if (found) {
                return Article(
                    results.getInt("id"),
                    results.getString("title"),
                    results.getString("text")
                )
            }
        }
        return null
    }

    override fun getCommentsByArticle(article: Int): List<Comment> {
        val comments = ArrayList<Comment>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM comments WHERE idArticle = ?;").use { stmt ->
                stmt.setInt(1, article)
                val results = stmt.executeQuery()
                while (results.next()) {
                    comments += Comment(
                        results.getInt("id"),
                        results.getInt("idArticle"),
                        results.getString("text")
                    )
                }
            }
        }
        return comments
    }

    override fun createComment(comment: Comment) {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO comments (id, idArticle, text) VALUES (NULL, ?, ?);").use { stmt ->
                stmt.setInt(1, comment.idArticle)
                stmt.setString(2, comment.text)
                stmt.executeUpdate()
            }
        }
    }

    override fun deleteComment(id: Int) {
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE comments.id = ?;").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

}