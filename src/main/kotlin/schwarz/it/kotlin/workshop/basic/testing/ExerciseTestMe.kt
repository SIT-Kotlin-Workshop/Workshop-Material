package schwarz.it.kotlin.workshop.basic.testing

fun testMe(candidates: List<String>): Int {
    var result = 0

    outer@ for (entry in candidates) {
        val candidate = entry.trim()

        if (candidate.isBlank()) {
            error("Input contains blank string")
        }

        for (i in 0..candidate.length / 2) {
            if (candidate[i].lowercaseChar() != candidate[candidate.lastIndex - i].lowercaseChar()) {
                continue@outer
            }
        }

        result++
    }

    return result
}