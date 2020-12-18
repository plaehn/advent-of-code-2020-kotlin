package org.plaehn.adventofcode.common

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

    fun neighbors(): List<Vector> =
        neighborOffsets()
            .map { offset -> this + offset }
            .toList()

    fun neighborOffsets() = neighborOffsets(dimension()).filter { !it.isCenter() }

    private fun neighborOffsets(dim: Int): List<Vector> {
        if (dim == 1) return (-1..1).map { Vector(it) }
        return sequence {
            (-1..1).forEach { offset ->
                neighborOffsets(dim - 1).forEach {
                    yield(Vector(it.values + listOf(offset)))
                }
            }
        }.toList()
    }

    fun dimension(): Int = values.count()

    private fun isCenter(): Boolean = values.all { it == 0 }
}