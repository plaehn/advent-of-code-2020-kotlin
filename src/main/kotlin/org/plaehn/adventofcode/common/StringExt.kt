package org.plaehn.adventofcode.common

fun String.tokenize(): List<String> = this.split("\\s+".toRegex()).filter { it.isNotBlank() }

fun String.groupByBlankLines(): List<String> = this.split("\r\n\\s*\r\n".toRegex())
