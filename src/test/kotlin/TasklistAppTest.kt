import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import repo.FakeRepository

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TasklistAppTest {
    lateinit var inputReader: InputReader
    lateinit var tableTasks: TableTasks
    lateinit var tasklistApp: TasklistApp
    lateinit var fakeRepository: FakeRepository

    @BeforeAll
    fun setUp() {
        inputReader = mockk()
        tableTasks = TableTasks()
        fakeRepository = FakeRepository()
        tasklistApp = TasklistApp(inputReader, tableTasks, fakeRepository)
    }

    @DisplayName("add Task with (priority=C, time=20:12, text= hello world, date= 2022-12-08)")
    @Test
    fun addTaskFirstScenario() {
        val expected = Task(
            id = 1,
            text = "hello world",
            date = "2022-08-08",
            time = "20:12",
            priority = "C"
        )
        every { inputReader.readAction() } returns "add" andThen "end"
        every { inputReader.readPriority() } returns "C"
        every { inputReader.readDate() } returns "2022-08-08"
        every { inputReader.readTime() } returns "20:12"
        every { inputReader.readTaskDescription() } returns "hello world"
        tasklistApp.start()
        assertTrue(fakeRepository.contains(expected))
    }

    @DisplayName("add Task with (priority=H, time=10:15, text=Hello from second scenario, date=2022-08-08)")
    @Test
    fun addTaskSecondScenario() {
        val expected = Task(
            id = 2,
            text = "Hello from second scenario",
            date = "2022-08-08",
            time = "10:15",
            priority = "H"
        )
        every { inputReader.readAction() } returns "add" andThen "end"
        every { inputReader.readPriority() } returns "H"
        every { inputReader.readDate() } returns "2022-08-08"
        every { inputReader.readTime() } returns "10:15"
        every { inputReader.readTaskDescription() } returns "Hello from second scenario"
        tasklistApp.start()
        assertTrue(fakeRepository.getAllTasks().contains(expected))
    }

}