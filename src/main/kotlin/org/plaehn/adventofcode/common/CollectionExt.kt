@file:Suppress("UnstableApiUsage")

package org.plaehn.adventofcode.common

import com.google.common.collect.Sets

fun <E> Set<E>.combinations(ofSize: Int): Set<Set<E>> = Sets.combinations(this, ofSize)

fun <T> Iterable<T>.findUnique(predicate: (T) -> Boolean): T? {
    val matches = filter(predicate)
    return if (matches.count() == 1) matches.first() else null
}