package util.collections

fun <K, V> Map<K, V?>.filterValuesNotNull(): Map<K, V> =
  filterValues { it != null }.mapValues { (_, v) -> v!! }

fun <K, V> Map<K, V>.join(other: Map<K, V>, merger: (V, V) -> V) =
  (keys + other.keys).associateWith { key ->
    val v1 = get(key)
    val v2 = other[key]
    (v1?.let { v2?.let { merger(v1, v2) } ?: v1 } ?: v2)!!
  }
