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

fun testMeRefactored(candidates: List<String>): Int =
    if (candidates.any { it.isBlank() }) {
        error("Input contains blank string")
    } else {
        candidates.count {
            val candidate = it.trim()
            (0..candidate.length / 2).all { i ->
                candidate[i].lowercaseChar() == candidate[candidate.lastIndex - i].lowercaseChar()
            }
        }
    }
