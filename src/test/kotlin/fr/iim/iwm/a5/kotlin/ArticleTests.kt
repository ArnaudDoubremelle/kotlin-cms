package fr.iim.iwm.a5.kotlin

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import fr.iim.iwm.a5.kotlin.Controller.Article.ArticleControllerImpl
import fr.iim.iwm.a5.kotlin.Model.Article
import fr.iim.iwm.a5.kotlin.Model.Model
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTests {
    @Test
    fun testArticleFound() {
        val model = mock<Model> {
            on  { getArticle(42) } doReturn Article(42, "super titre", "text text text")
        }

        val articleController = ArticleControllerImpl(model)

        val result = articleController.startFM(42)
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testArticleNotFound() {
        val model = mock<Model> {}

        val articleController = ArticleControllerImpl(FakeModel())

        val result = articleController.startFM(55)
        assertEquals(HttpStatusCode.NotFound, result)
    }
}