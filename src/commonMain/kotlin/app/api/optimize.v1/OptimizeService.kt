package app.api.optimize.v1

import app.serialization.AppJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

interface OptimizeService {
  companion object {
    protected fun encodeRequest(request: OptimizeRequest) = AppJson.encodeToString(request)
    protected fun decodeRequest(serial: String) = AppJson.decodeFromString<OptimizeRequest>(serial)

    protected fun encodeResponse(response: OptimizeResponse) = AppJson.encodeToString(response)
    protected fun decodeResponse(serial: String) = AppJson.decodeFromString<OptimizeResponse>(serial)
  }

  suspend fun optimize(request: OptimizeRequest): OptimizeResponse
}
