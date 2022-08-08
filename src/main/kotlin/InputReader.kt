interface InputReader {
    fun readDate(): String
    fun readTime(): String
    fun readPriority(): String
    fun readAction(): String
    fun readTaskDescription(): String
    fun readLines(predicate: (line: String) -> Boolean): String
    fun readlineUntil(predicate: (input: String) -> Boolean)
}