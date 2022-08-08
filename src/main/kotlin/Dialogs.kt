// Date
fun showInputDateDialog() = println("Input the date (yyyy-mm-dd):")
fun showInvalidDateDialog() = println("The input date is invalid")

// Time
fun showInputTimeDialog() = println("Input the time (hh:mm):")
fun showInvalidTimeDialog() = println("The input time is invalid")

// Priority
fun showAskPriorityDialog() = println("Input the task priority (${Priority.values().joinToString(", ")}):")

fun showEmptyTaskListDialog() = println("No tasks have been input")
fun showAskAddTaskDialog() = println("Input a new task (enter a blank line to end):")
fun showBlankTaskDialog() = println("The task is blank")

// Exiting
fun showAppExitingDialog() = println("Tasklist exiting!")

// Actions
fun showAskActionDialog() =
    println("Input an action (${Action.values().joinToString(", ") { it.name.lowercase() }}):")

fun showInvalidActionDialog() = println("The input action is invalid")

//
fun showDeletedTaskDialog() = println("The task is deleted")

// Editing & Deleting
fun showInputFieldToEditDialog() = println("Input a field to edit (priority, date, time, task):")
fun showInvalidFieldDialog() = println("Invalid field")
fun showTaskEditedDialog() = println("The task is changed")
fun showInputTaskNumberDialog(maxTaskNumber: Int) =
    println("Input the task number (1-$maxTaskNumber):")

fun showInvalidTaskNumberDialog() = println("Invalid task number")



