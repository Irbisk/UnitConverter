package converter

fun main() {
    while (true) {
        println("Enter what you want to convert (or exit):")
        val input = readln().lowercase()
        if (input == "exit") {
            break
        } else if (!parseIsCorrect(input)) {
            println("Parse error")
        } else {
            val inputArray = input.split(" ")
                .filter { it != "degree" && it != "degrees"}
            val measureFrom = inputArray[1]
            val measureTo = inputArray[3]
            val numberFrom = inputArray[0].toDouble()

            if (!convertIsPossible(measureFrom, measureTo)) {
                println("Conversion from ${getStringEnum(measureFrom)} to ${getStringEnum(measureTo)} is impossible")
            } else if (isNegative(numberFrom, findEnum(measureFrom)!!)) {
                if (findEnum(measureFrom)!!.group == 0) {
                    println("Length shouldn't be negative")
                } else {
                    println("Weight shouldn't be negative")
                }
            } else {
                convert(numberFrom, findEnum(measureFrom)!!, findEnum(measureTo)!!)
            }
            println()
        }
    }
}

fun parseIsCorrect(input: String): Boolean {
    val unit ="(degrees?)? ?\\w+".toRegex()
    val reg = "-?(\\d+\\.?\\d*) ($unit|$unit|$unit) (\\w+) ($unit|$unit|$unit)".toRegex()
    return input.lowercase().matches(reg)
}

fun findEnum(measure: String): Measures? {
    return Measures.values().find { measure in it.designation }
}

fun getStringEnum(measure: String): String {
    return if (findEnum(measure) == null ) "???" else findEnum(measure)!!.designation[2]
}

fun isNegative(numberFrom: Double, measureFrom: Measures): Boolean {
    return (measureFrom.group != 2 && numberFrom < 0)
}

fun convertIsPossible(measureFrom: String, measureTo: String): Boolean {
    val fromEnum = findEnum(measureFrom)
    val toEnum = findEnum(measureTo)

    return if (fromEnum == null || toEnum == null) {
        false
    } else (fromEnum.group == toEnum.group)
}

fun convert(numberFrom: Double, measureFrom: Measures, measureTo: Measures) {
    val numberResult = if (measureFrom.group == 2) {
        convertDegrees(numberFrom, measureFrom, measureTo)
    } else {
        numberFrom * measureFrom.hub / measureTo.hub
    }

    val mf = if (numberFrom == 1.0) measureFrom.designation[1] else measureFrom.designation[2]
    val mt = if (numberResult == 1.0) measureTo.designation[1] else measureTo.designation[2]

    println("$numberFrom $mf is $numberResult $mt")
}

fun convertDegrees(numberFrom: Double, measureFrom: Measures, measureTo: Measures): Double {
    return when {
        "c" in measureFrom.designation && "f" in measureTo.designation -> numberFrom * 9 / 5 + 32
        "f" in measureFrom.designation && "c" in measureTo.designation -> (numberFrom - 32) * 5 / 9
        "c" in measureFrom.designation && "k" in measureTo.designation -> numberFrom + 273.15
        "k" in measureFrom.designation && "c" in measureTo.designation -> numberFrom - 273.15
        "f" in measureFrom.designation && "k" in measureTo.designation -> (numberFrom + 459.67) * 5 / 9
        "k" in measureFrom.designation && "f" in measureTo.designation -> numberFrom * 9 / 5 - 459.67
        measureFrom == measureTo -> numberFrom
        else -> 0.0
    }
}