package tasklist

fun priorityToColor(priority: Priority): String {
    return when (priority) {
        Priority.C -> "\u001B[101m \u001B[0m"
        Priority.H -> "\u001B[103m \u001B[0m"
        Priority.N -> "\u001B[102m \u001B[0m"
        Priority.L -> "\u001B[104m \u001B[0m"
    }
}

fun dueTagToColor(dueTag: Char): String {
    return when (dueTag) {
        'I' -> "\u001B[102m \u001B[0m"
        'T' -> "\u001B[103m \u001B[0m"
        'O' -> "\u001B[101m \u001B[0m"
        else -> throw Exception("Unknown due tag")
    }

}