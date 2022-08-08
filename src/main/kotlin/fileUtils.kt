import java.io.File

const val tasksFileName = "tasklist.json"
val jsonTasksFile = File(tasksFileName)

fun saveTaskInFile(tasks: List<Task>) {
    val jsonFile = File(tasksFileName)
    tasksToJson(tasks)?.let { jsonFile.writeText(it) }
}

fun getTasksFromFile(tasksFile: File): List<Task> {
    val localJson = tasksFile.readText()
    return jsonToTasks(localJson) ?: emptyList()
}
