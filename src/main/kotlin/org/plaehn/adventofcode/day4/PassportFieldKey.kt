package org.plaehn.adventofcode.day4

//byr (Birth Year) - four digits; at least 1920 and at most 2002.
//iyr (Issue Year) - four digits; at least 2010 and at most 2020.
//eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
//hgt (Height) - a number followed by either cm or in:
//If cm, the number must be at least 150 and at most 193.
//If in, the number must be at least 59 and at most 76.
//hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
//ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
//pid (Passport ID) - a nine-digit number, including leading zeroes.
//cid (Country ID) - ignored, missing or not.

enum class PassportFieldKey(val shortForm: String) {

    BIRTH_YEAR("byr") {
        override fun isValid(value: String): Boolean = isNumberInRange(value, 1920..2002)
    },
    ISSUE_YEAR("iyr") {
        override fun isValid(value: String): Boolean = isNumberInRange(value, 2010..2020)
    },
    EXPIRATION_YEAR("eyr") {
        override fun isValid(value: String): Boolean = isNumberInRange(value, 2020..2030)
    },
    HEIGHT("hgt") {
        override fun isValid(value: String): Boolean = when (value.takeLast(2)) {
            "cm" -> isNumberInRange(value.dropLast(2), 150..193)
            "in" -> isNumberInRange(value.dropLast(2), 59..76)
            else -> false
        }
    },
    HAIR_COLOR("hcl") {
        override fun isValid(value: String): Boolean = value.matches("^#[0-9a-f]{6}$".toRegex())
    },
    EYE_COLOR("ecl") {
        private val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        override fun isValid(value: String): Boolean = validEyeColors.contains(value)
    },
    PASSPORT_ID("pid") {
        override fun isValid(value: String): Boolean = value.matches("[0-9]{9}".toRegex())
    },
    COUNTRY_ID("cid") {
        override fun isValid(value: String): Boolean = true
    };

    abstract fun isValid(value: String): Boolean

    fun isNumberInRange(value: String, range: IntRange): Boolean {
        return try {
            range.contains(value.toInt())
        } catch (exx: NumberFormatException) {
            false
        }
    }

    companion object {
        fun from(shortForm: String): PassportFieldKey = values()
                .find { it.shortForm == shortForm }
                ?: throw IllegalArgumentException("Unknown passport field key $shortForm")
    }
}


