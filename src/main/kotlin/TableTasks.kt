/* Table
        +----+------------+-------+---+---+--------------------------------------------+
        | N  |    Date    | Time  | P | D |                   Task                     |
        +----+------------+-------+---+---+--------------------------------------------+
        | 1  | yyyy-MM-dd | hh:mm |   |   |                                            |
        |    |            |       |   |   |                                            |
        |    |            |       |   |   |                                            |
        +----+------------+-------+---+---+--------------------------------------------+
        */


class TableTasks {
    private val taskTextLineLength = 44
    private fun drawHeader() {
        println(
            """ 
        +----+------------+-------+---+---+--------------------------------------------+
        | N  |    Date    | Time  | P | D |                   Task                     |
        +----+------------+-------+---+---+--------------------------------------------+
        """.trimIndent()
        )

    }

    fun draw(tasks: List<Task>) {
        drawHeader()
        tasks.forEach {
            drawTask(it)
        }
    }

    private fun drawTask(task: Task) {
        //  Print first line
        val id = task.id
        val idRightMargin = if (id in 1..9) "  " else " "
        val priorityColor = priorityToColor(Priority.valueOf(task.priority.uppercase()))
        val dueTagColor = dueTagToColor(task.dueDateTag)
        val date = task.date
        val time = task.time
        val textLines = breakToLines(task.text, taskTextLineLength)
        val firstLine = textLines.first()

        textLines.forEachIndexed { index, line ->
            val lineExtraSpace =
                if (line.length < taskTextLineLength) " ".repeat(taskTextLineLength - line.length) else ""
            // Print first line
            if (index == 0) println(
                "| $id$idRightMargin| $date | $time | $priorityColor | $dueTagColor |$firstLine$lineExtraSpace|"
            )
            //  Print other lines
            else {
                if (line.length < taskTextLineLength) " ".repeat(taskTextLineLength - line.length) else ""
                println(
                    "|    |            |       |   |   |${line}$lineExtraSpace|"
                )
            }

        }
        //  Footer of task
        println(
            "+----+------------+-------+---+---+--------------------------------------------+"
        )

    }

    private fun breakToLines(text: String, linesLength: Int): List<String> {
        val brokenText = StringBuilder()
        val textLines = text.lines().toMutableList()
        textLines.forEach { line ->
            line.chunked(linesLength).forEach { chunkedLine ->
                brokenText.appendLine(chunkedLine)
            }
        }
        val list = brokenText.lines().toMutableList()
        list.removeIf { it.isBlank() }
        return list
    }


}