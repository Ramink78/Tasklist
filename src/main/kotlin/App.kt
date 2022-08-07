package tasklist

import java.time.LocalDate
import java.time.LocalTime
import java.util.*

private val tableTasks: TableTasks by lazy {
    TableTasks()
}
fun main() {
    if (jsonTasksFile.exists())
        TaskRepository.addAll(getTasksFromFile(jsonTasksFile))
    mainMenu()
}

fun askForPriority(): String {
    while (true) {
        showAskPriorityDialog()
        val priority = readln().uppercase()
        if (Priority.values().any { it.name == priority.uppercase() }
        ) return priority
        else continue
    }
}

fun askForTime(): String {

    while (true) {
        println("Input the time (hh:mm):")
        try {
            val time = readln().split(':').map { it.toInt() }
            val hours = time[0]
            val minutes = time[1]
            val localTime = LocalTime.of(hours, minutes)
            return "${localTime.hour.toString().toTwoDigit()}:${localTime.minute.toString().toTwoDigit()}"
        } catch (e: Exception) {
            println("The input time is invalid")
            continue
        }
    }
}

fun askForDate(): String {
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            val date = readln().split('-').map { it.toInt() }
            val year = date[0]
            val month = date[1]
            val dayOfMonth = date[2]
            val localDate = LocalDate.of(year, month, dayOfMonth)
            return "${localDate.year}-${
                localDate.monthValue.toString().toTwoDigit()
            }-${(localDate.dayOfMonth).toString().toTwoDigit()}"
        } catch (e: Exception) {
            println("The input date is invalid")
            continue
        }
    }
}

fun askForTaskDescription(date: String, priority: String, time: String) {
    val scanner = Scanner(System.`in`)
    val taskText = StringBuilder()
    showAskAddTaskDialog()
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isBlank() and taskText.isEmpty()) {
            showBlankTaskDialog()
            return
        }
        if (line.isBlank()) {
            TaskRepository.addTask(
                Task(
                    text = taskText.toString(),
                    priority = priority,
                    date = date,
                    time = time,
                )
            )
            return
        } else taskText.appendLine(line.trim())
    }
}

fun printAllTasks() {
    if (TaskRepository.isEmpty) showEmptyTaskListDialog()
    else tableTasks.draw(TaskRepository.getAllTasks())
}

fun askForAddTask() {
    val priority = askForPriority()
    val date = askForDate()
    val time = askForTime()
    askForTaskDescription(date, priority, time)
}

fun askForEditTaskDescription(): String {
    val scanner = Scanner(System.`in`)
    val taskText = StringBuilder()
    showAskAddTaskDialog()
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isBlank()) {
            return taskText.toString()
        } else {
            taskText.appendLine(line)
            continue
        }
    }
    return taskText.toString()
}

fun askForTaskNumber(): Int {
    while (true) {
        showInputTaskNumberDialog(TaskRepository.lastId)
        val taskId = readln().toIntOrNull()
        if (taskId in 1..TaskRepository.lastId && taskId != null) {
            return taskId
        } else {
            showInvalidTaskNumberDialog()
            continue
        }
    }
}

fun mainMenu() {
    while (true) {
        showAskActionDialog()
        val action = readln()
        when (action) {
            Menu.ADD.title -> {
                askForAddTask()
            }
            Menu.PRINT.title -> {
                printAllTasks()
            }
            Menu.END.title -> {
                showAppExitingDialog()
                saveTaskInFile(TaskRepository.getAllTasks())
                return
            }
            Menu.DELETE.title -> {
                if (!TaskRepository.isEmpty) {
                    printAllTasks()
                    val taskId = askForTaskNumber()
                    TaskRepository.removeTask(taskId)
                    showDeletedTaskDialog()
                } else showEmptyTaskListDialog()


            }
            Menu.EDIT.title -> {
                if (!TaskRepository.isEmpty) {
                    printAllTasks()
                    editField(
                        taskId = askForTaskNumber(),
                        field = askFieldToEdit()
                    )
                } else showEmptyTaskListDialog()

            }
            else -> {
                showInvalidActionDialog()
                continue
            }
        }
    }
}

fun editField(field: String, taskId: Int) {
    when (field) {
        "priority" -> {
            val newPriority = askForPriority()
            TaskRepository.editTask(taskId, TaskRepository.get(taskId).copy(priority = newPriority))
            showTaskEditedDialog()
        }
        "date" -> {
            val newDate = askForDate()
            TaskRepository.editTask(taskId, TaskRepository.get(taskId).copy(date = newDate))
            showTaskEditedDialog()
        }
        "time" -> {
            val newTime = askForTime()
            TaskRepository.editTask(taskId, TaskRepository.get(taskId).copy(time = newTime))
            showTaskEditedDialog()
        }
        "task" -> {
            val newTaskDescription = askForEditTaskDescription()
            TaskRepository.editTask(taskId, TaskRepository.get(taskId).copy(text = newTaskDescription))
            showTaskEditedDialog()
        }
        else -> showInvalidFieldDialog()
    }
}

fun askFieldToEdit(): String {
    while (true) {
        showInputFieldToEditDialog()
        when (val field = readln()) {
            in listOf("priority", "date", "time", "task") -> return field

            else -> {
                showInvalidFieldDialog()
                continue
            }
        }
    }
}

fun String.toTwoDigit(): String {
    return if (length < 2) "0$this" else this
}