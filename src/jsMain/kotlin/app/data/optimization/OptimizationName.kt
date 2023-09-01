package app.data.optimization

import app.api.common.ResourceName

data class OptimizationName(val id: Int) : ResourceName {
  override fun getResourceName() = "optimizations/${id.toUInt()}"
}
