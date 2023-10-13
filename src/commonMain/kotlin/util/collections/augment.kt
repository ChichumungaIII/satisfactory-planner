package util.collections

import util.function.using

fun <T, A, D> Iterable<T>.augment(initial: D, integrate: MutableList<A>.(D, T) -> D): Augmentation<A, D> {
    val result = mutableListOf<A>()
    val residue = fold(initial, using(result, integrate))
    return Augmentation(result, residue)
}

data class Augmentation<A, D>(val augment: List<A>, val residue: D)
