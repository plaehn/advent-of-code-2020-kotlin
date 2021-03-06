package org.plaehn.adventofcode.common

import com.google.common.collect.Sets

data class Grid<T>(private val data: Map<Vector, T>) {

    fun min(): Vector = Vector((0 until dimensions())
                                   .map { index -> data.keys.minOf { vector -> vector[index] } }
                                   .toList())

    fun max(): Vector = Vector((0 until dimensions())
                                   .map { index -> data.keys.maxOf { vector -> vector[index] } }
                                   .toList())

    private fun dimensions(): Int = data.keys.first().dimension()

    operator fun get(position: Vector): T? = data[position]

    fun updated(positionToUpdate: Vector, newValue: T): Grid<T> {
        val entries = data.entries.map { (position, value) ->
            position to if (position == positionToUpdate) newValue else value
        }.toMutableList()
        if (!data.containsKey(positionToUpdate)) {
            entries.add(positionToUpdate to newValue)
        }
        return Grid(entries.toMap())
    }

    fun values() = data.values

    fun enumerateCubesSpannedBy(min: Vector, max: Vector) =
        Sets.cartesianProduct((0 until dimensions()).map { (min[it]..max[it]).toSet() })
            .map { Vector(it) }

    companion object {
        fun <T> from2DInput(dimensions: Int, lines: List<String>, charToValue: (Char) -> T): Grid<T> = Grid(
            sequence {
                lines.forEachIndexed { x, line ->
                    line.forEachIndexed { y, state ->
                        val active = charToValue(state)
                        val vector = Vector(listOf(x, y) + List(dimensions - 2) { 0 })
                        yield(vector to active)
                    }
                }
            }.toMap()
        )
    }
}