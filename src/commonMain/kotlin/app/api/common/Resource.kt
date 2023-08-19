package app.api.common

interface Resource<N : ResourceName> {
  val name: N
}
