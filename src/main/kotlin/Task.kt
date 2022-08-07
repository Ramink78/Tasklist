package tasklist

data class Task(
    val text: String,
    val priority: String,
    val date: String,
    val time: String,
) {
     val dueDateTag: Char
        get() = getDueTag(date)
    val id: Int
        get() = TaskRepository.getAllTasks().indexOf(this) + 1

    override fun toString(): String {
        val taskTextLines = text.lines()
        val idRightMargin = if (id in 1..9) "  " else " "
        val firstLine = "${id}$idRightMargin${date} $time $priority $dueDateTag"
        val lineLeftMargin = " ".repeat(id.toString().length) + idRightMargin
        return buildString {
            appendLine(firstLine)
            taskTextLines.forEach { line ->
                appendLine("$lineLeftMargin$line")
            }
        }
    }
}
