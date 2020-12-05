package org.plaehn.adventofcode.day4

enum class PassportFieldKey(val shortForm: String) {

    BIRTH_YEAR("byr") {
        override fun isValid(value: String) = isNumberInRange(value, 1920..2002)
    },
    ISSUE_YEAR("iyr") {
        override fun isValid(value: String) = isNumberInRange(value, 2010..2020)
    },
    EXPIRATION_YEAR("eyr") {
        override fun isValid(value: String) = isNumberInRange(value, 2020..2030)
    },
    HEIGHT("hgt") {
        override fun isValid(value: String) = when (value.takeLast(2)) {
            "cm" -> isNumberInRange(value.dropLast(2), 150..193)
            "in" -> isNumberInRange(value.dropLast(2), 59..76)
            else -> false
        }
    },
    HAIR_COLOR("hcl") {
        override fun isValid(value: String) = value.matches("^#[0-9a-f]{6}$".toRegex())
    },
    EYE_COLOR("ecl") {
        private val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        override fun isValid(value: String) = validEyeColors.contains(value)
    },
    PASSPORT_ID("pid") {
        override fun isValid(value: String) = value.matches("[0-9]{9}".toRegex())
    },
    COUNTRY_ID("cid") {
        override fun isValid(value: String) = true
    };

    abstract fun isValid(value: String): Boolean

    fun isNumberInRange(value: String, range: IntRange): Boolean = try {
        range.contains(value.toInt())
    } catch (exx: NumberFormatException) {
        false
    }

    companion object {
        fun from(shortForm: String) = values()
                .find { it.shortForm == shortForm }
                ?: throw IllegalArgumentException("Unknown passport field key $shortForm")
    }
}
