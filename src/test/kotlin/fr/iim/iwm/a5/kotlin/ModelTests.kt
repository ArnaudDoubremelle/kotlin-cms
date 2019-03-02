package fr.iim.iwm.a5.kotlin

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ModelTests {
    val model = MysqlModel("jdbc:h2:mem:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use { connection ->
            connection.prepareStatement("""
                DROP TABLE IF EXISTS articles;
                CREATE TABLE articles (
                  id int(11) NOT NULL AUTO_INCREMENT,
                  title varchar(255) NOT NULL,
                  text text NOT NULL,
                  PRIMARY KEY (id)
                );
                INSERT INTO articles VALUES
                  (1, 'article1', 'Iam in altera philosophiae parte. quae est quaerendi ac disserendi, quae logikh dicitur, iste vester plane, ut mihi quidem videtur, inermis ac nudus est. tollit definitiones, nihil de dividendo ac partiendo docet, non quo modo efficiatur concludaturque ratio tradit, non qua via captiosa solvantur ambigua distinguantur ostendit; iudicia rerum in sensibus ponit, quibus si semel aliquid falsi pro vero probatum sit, sublatum esse omne iudicium veri et falsi putat.'),
                  (2, 'article2', 'Et quoniam apud eos ut in capite mundi morborum acerbitates celsius dominantur, ad quos vel sedandos omnis professio medendi torpescit, excogitatum est adminiculum sospitale nequi amicum perferentem similia videat, additumque est cautionibus paucis remedium aliud satis validum, ut famulos percontatum missos quem ad modum valeant noti hac aegritudine colligati, non ante recipiant domum quam lavacro purgaverint corpus. ita etiam alienis oculis visa metuitur labes.')"""
            ).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testsArticleInDb() {
        val article = model.getArticle(1)

        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("article1", article.title)
        assertTrue(article.text!!.startsWith("Iam in altera"))
    }

    @Test
    fun testsArticleNotInDb() {
        val article = model.getArticle(3)

        assertNull(article)
    }
}