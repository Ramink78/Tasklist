package tasklist

fun showEmptyTaskListDialog() = println("No tasks have been input")
fun showAskPriorityDialog() = println("Input the task priority (${Priority.values().joinToString(", ")}):")
fun showAskAddTaskDialog() = println("Input a new task (enter a blank line to end):")
fun showBlankTaskDialog() = println("The task is blank")
fun showAskActionDialog() =
    println("Input an action (${Menu.values().map { it.name.lowercase() }.joinToString(", ")}):")

fun showAppExitingDialog() = println("Tasklist exiting!")
fun showInvalidActionDialog() = println("The input action is invalid")
fun showInputTaskNumberDialog(maxTaskNumber: Int) =
    println("Input the task number (1-$maxTaskNumber):")

fun showInvalidTaskNumberDialog() = println("Invalid task number")
fun showDeletedTaskDialog() = println("The task is deleted")
fun showInputFieldToEditDialog() = println("Input a field to edit (priority, date, time, task):")
fun showInvalidFieldDialog() = println("Invalid field")
fun showTaskEditedDialog() = println("The task is changed")