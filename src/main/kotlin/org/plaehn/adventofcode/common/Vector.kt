package org.plaehn.adventofcode.common

import com.google.common.collect.Sets

data class Vector(val values: List<Int>) {

    val x = values[0]
    val y = if (dimension() > 1) values[1] else 0
    val z = if (dimension() > 2) values[2] else 0

    constructor(vararg values: Int) : this(values.toList())

    operator fun get(i: Int): Int = values[i]

    operator fun plus(other: Vector): Vector = Vector(values.zip(other.values) { v1, v2 -> v1 + v2 })

    operator fun plus(value: Int): Vector = Vector(values.map { it + value })

    operator fun minus(other: Vector): Vector = Vector(values.zip(other.values) { v1, v2 -> v1 - v2 })

    operator fun minus(value: Int): Vector = Vector(values.map { it - value })

    operator fun times(value: Int): Vector = Vector(values.map { it * value })

    fun neighbors() =
        neighborOffsets()
            .map { offset -> this + offset }
            .toList()

    fun neighborOffsets() =
        Sets
            .cartesianProduct(List(dimension()) { (-1..1).toSet() })
            .map { Vector(it) }
            .filter { !it.isCenter() }

    fun dimension(): Int = values.count()

    private fun isCenter(): Boolean = values.all { it == 0 }
}