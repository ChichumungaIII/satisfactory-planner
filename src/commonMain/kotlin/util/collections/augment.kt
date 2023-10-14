package util.collections

import util.function.using

fun <T, A, D, O> Iterable<T>.augment(
  initial: D,
  transform: (List<A>) -> O,
  integrate: MutableList<A>.(D, T) -> D,
): Augmentation<O, D> {
  val result = mutableListOf<A>()
  val residue = fold(initial, using(result, integrate))
  return Augmentation(transform(result), residue)
}

data class Augmentation<O, D>(val augment: O, val residue: D)
