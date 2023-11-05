package util.collections

fun <T> List<T>.swap(i: Int, j: Int) =
  toMutableList().also {
    it[i] = get(j)
    it[j] = get(i)
  }.toList()
