package app

enum class RouteParams(val key: String) {
  SAVE_ID("saveId"),
  PLAN_ID("planId");

  companion object {
    fun parseInt(param: String?) = parseInt(param) { i -> i }
    fun <T> parseInt(param: String?, transform: ((Int) -> T)) =
      param?.toUIntOrNull()?.toInt()?.let(transform)
  }

  infix fun to(id: Int) = key to id.toUInt().toString()
}
