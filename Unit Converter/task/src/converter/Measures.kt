package converter

enum class Measures (val designation: List<String>, val hub: Double, val group: Int) {
    METERS( listOf("m", "meter", "meters"), 1.0, 0),
    KILOMETERS(listOf("km", "kilometer", "kilometers"), 1000.0, 0),
    CENTIMETERS(listOf("cm", "centimeter", "centimeters"), 0.01, 0),
    MILLIMETERS(listOf("mm", "millimeter", "millimeters"), 0.001, 0),
    MILES(listOf("mi", "mile", "miles"),1609.35, 0),
    YARDS(listOf("yd", "yard", "yards"), 0.9144, 0),
    FEET(listOf("ft", "foot", "feet"), 0.3048, 0),
    INCHES(listOf("in", "inch", "inches"), 0.0254, 0),
    GRAMS(listOf("g","gram","grams"), 1.0, 1),
    KILOGRAMS(listOf("kg", "kilogram", "kilograms"),1000.0, 1),
    MILLIGRAMS(listOf("mg", "milligram", "milligrams"), 0.001, 1),
    POUNDS(listOf("lb", "pound", "pounds"), 453.592, 1),
    OUNCES(listOf("oz", "ounce", "ounces"), 28.3495, 1),
    CELCIUS(listOf("c", "degree Celsius", "degrees Celsius", "celsius", "dc", "degrees celsius" , "degree celsius"), 0.0, 2),
    FAHRENHEIT(listOf("f", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "degrees fahrenheit","degree fahrenheit"), 0.0, 2),
    KELVINS(listOf("k", "kelvin", "kelvins"), 0.0, 2)
}