fun String.toTwoDigit(): String {
    return if (length < 2) "0$this" else this
}