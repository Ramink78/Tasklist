import repo.TaskRepository
import repo.TaskRepositoryImpl

fun main() {
    val tasklistApp = TasklistApp(InputReaderImpl(), TableTasks(), TaskRepositoryImpl())
    tasklistApp.start()
}

class TasklistApp(
    private val inputReader: InputReader,
    private val tableTasks: TableTasks,
    private val repository: TaskRepository
) {

    fun start() {
        while (true) {
            showAskActionDialog()
            try {
                val action = enumValueOf<Action>(inputReader.readAction().uppercase())
                performAction(action)
                // Exiting
                if (action == Action.END) return
            } catch (e: IllegalArgumentException) {
                showInvalidActionDialog()
                continue
            }
        }
    }


    private fun askForTaskDescription(): String {
        showAskAddTaskDialog()
        return inputReader.readTaskDescription()
    }

    private fun askForDate(): String {
        return inputReader.readDate()
    }

    private fun askForPriority(): String {
        showAskPriorityDialog()
        return inputReader.readPriority()
    }

    private fun askForTime(): String {
        return inputReader.readTime()
    }

    private fun askForAddTask() {
        val priority = askForPriority()
        val date = askForDate()
        val time = askForTime()
        val desc = askForTaskDescription()
        if (desc.isBlank()) {
            showBlankTaskDialog()
            return
        }
        repository.addTask(
            Task(
                text = desc,
                priority = priority,
                date = date,
                time = time
            )
        )
    }

    private fun printAllTasks() {
        if (repository.isEmpty) showEmptyTaskListDialog()
        else tableTasks.draw(repository.getAllTasks())
    }

    private fun askForEditTaskDescription(): String {
        showAskAddTaskDialog()
        val taskText = StringBuilder()
        inputReader.readLines() { line ->
            if (line.isBlank()) {
                false

            } else {
                taskText.appendLine(line)
                true
            }
        }
        return taskText.toString()
    }

    private fun askForTaskNumber(): Int {
        while (true) {
            showInputTaskNumberDialog(repository.lastId)
            val taskId = inputReader.readAction().toIntOrNull()
            if (taskId in 1..repository.lastId && taskId != null) {
                return taskId
            } else {
                showInvalidTaskNumberDialog()
                continue
            }
        }
    }

    private fun askFieldToEdit(): String {
        while (true) {
            showInputFieldToEditDialog()
            when (val field = inputReader.readAction()) {
                in listOf("priority", "date", "time", "task") -> return field

                else -> {
                    showInvalidFieldDialog()
                    continue
                }
            }
        }
    }

    private fun editField(field: String, taskId: Int) {
        when (field) {
            "priority" -> {
                val newPriority = askForPriority()
                repository.edit(taskId, repository.get(taskId).copy(priority = newPriority))
                showTaskEditedDialog()
            }
            "date" -> {
                val newDate = askForDate()
                repository.edit(taskId, repository.get(taskId).copy(date = newDate))
                showTaskEditedDialog()
            }
            "time" -> {
                val newTime = askForTime()
                repository.edit(taskId, repository.get(taskId).copy(time = newTime))
                showTaskEditedDialog()
            }
            "task" -> {
                val newTaskDescription = askForEditTaskDescription()
                repository.edit(taskId, repository.get(taskId).copy(text = newTaskDescription))
                showTaskEditedDialog()
            }
            else -> showInvalidFieldDialog()
        }
    }

    private fun performAction(action: Action) {
        when (action) {
            Action.ADD -> {
                askForAddTask()
            }
            Action.PRINT -> {
                printAllTasks()
            }
            Action.EDIT -> {
                if (!repository.isEmpty) {
                    printAllTasks()
                    editField(
                        taskId = askForTaskNumber(),
                        field = askFieldToEdit()
                    )
                } else showEmptyTaskListDialog()
            }
            Action.DELETE -> {
                if (!repository.isEmpty) {
                    printAllTasks()
                    val taskId = askForTaskNumber()
                    repository.remove(taskId)
                    showDeletedTaskDialog()
                } else showEmptyTaskListDialog()
            }
            Action.END -> {
                showAppExitingDialog()
                saveTaskInFile(repository.getAllTasks())
            }
        }
    }

}