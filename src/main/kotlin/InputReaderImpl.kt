import java.time.LocalDate
import java.time.LocalTime

class InputReaderImpl : InputReader {

    override fun readDate(): String {
        while (true) {
            showInputDateDialog()
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
                showInvalidDateDialog()
                continue
            }
        }
    }

    override fun readTime(): String {
        while (true) {
            showInputTimeDialog()
            try {
                val time = readln().split(':').map { it.toInt() }
                val hours = time[0]
                val minutes = time[1]
                val localTime = LocalTime.of(hours, minutes)
                return "${localTime.hour.toString().toTwoDigit()}:${localTime.minute.toString().toTwoDigit()}"
            } catch (e: Exception) {
                showInvalidTimeDialog()
                continue
            }
        }
    }

    override fun readPriority(): String {
        while (true) {
            val priority = readln()
            if (Priority.values().any { it.name == priority.uppercase() }) {
                return priority
            } else {
                showAskPriorityDialog()
                continue
            }
        }
    }

    override fun readAction(): String {
        return readln()
    }

    override fun readTaskDescription(): String {
        return readLines {
            it.isNotEmpty()
        }
    }

    override fun readLines(predicate: (line: String) -> Boolean): String {
        val wholeLines = StringBuilder()
        do {
            val line = readln()
            wholeLines.appendLine(line)
        } while (predicate(line))
        return wholeLines.toString()
    }

    override fun readlineUntil(predicate: (input: String) -> Boolean) {
        while (true) {
            val input = readAction()
            if (predicate(input)) return else continue
        }
    }
}