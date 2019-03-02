package fr.iim.iwm.a5.kotlin

class MysqlModel : IMysqlModel {

    val connectionPool = ConnectionPool("jdbc:mysql://localhost:3306/cms", "root", "root")

    override fun getArticlesList(): List<Article> {
        val articles = ArrayList<Article>()
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id, title FROM articles").use { stmt ->
                val results = stmt.executeQuery()

                while (results.next()) {
                    articles += Article(results.getInt("id"), results.getString("title"))
                }
            }
        }
        return articles
    }

    override fun getArticle(id: Int): Article? {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id, title, text FROM articles WHERE id = ?").use { stmt ->
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
        }
        return null
    }

}