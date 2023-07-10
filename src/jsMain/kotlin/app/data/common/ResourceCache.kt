package app.data.common

abstract class ResourceCache<N, R> {
  private val cache = mutableMapOf<N, R>()

  operator fun get(name: N) = cache[name]

  fun insert(resource: R) {
    cache[getName(resource)] = resource
  }

  fun insertAll(resources: Collection<R>) {
    resources.forEach { insert(it) }
  }

  abstract fun getName(resource: R): N
}
