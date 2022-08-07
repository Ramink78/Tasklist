package tasklist

import kotlinx.datetime.*

/*
   O -> Overdue
   I -> In time
   T -> Today
 */
fun getDueTag(dueDate: String): Char {
    val taskDueDate = LocalDate.parse(dueDate)
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
    return when {
        currentDate.daysUntil(taskDueDate) == 0 -> 'T'
        currentDate.daysUntil(taskDueDate) < 0 -> 'O'
        else -> 'I'
    }
}