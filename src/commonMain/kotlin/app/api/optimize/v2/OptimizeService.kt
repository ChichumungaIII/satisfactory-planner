package app.api.optimize.v2

import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse
import app.serialization.AppJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

interface OptimizeService {
  companion object {
    private val OptimizeJson = AppJson

    protected fun encodeRequest(request: OptimizeRequest) = OptimizeJson.encodeToString(request)
    protected fun decodeRequest(serial: String) = OptimizeJson.decodeFromString<OptimizeRequest>(serial)

    protected fun encodeResponse(response: OptimizeResponse) = OptimizeJson.encodeToString(response)
    protected fun decodeResponse(serial: String) = OptimizeJson.decodeFromString<OptimizeResponse>(serial)
  }

  suspend fun optimize(request: OptimizeRequest): OptimizeResponse
}
