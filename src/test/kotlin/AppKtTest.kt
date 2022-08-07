import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppKtTest {

    val outContent = ByteArrayOutputStream()
    val systemOut = System.out
    val systemErr = System.err

    @BeforeAll
    fun setupOutStreams() {
        System.setOut(systemOut)
        System.setErr(systemErr)
    }

    @Test
    fun testMainMenu() {
        println("Input an action (add, print, edit, delete, end):")
        assertEquals("Input an action (add, print, edit, delete, end):", outContent.toString())
    }

}