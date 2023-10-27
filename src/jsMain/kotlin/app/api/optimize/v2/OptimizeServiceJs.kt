package app.api.optimize.v2

import app.api.optimize.v2.OptimizeService.Companion.decodeResponse
import app.api.optimize.v2.OptimizeService.Companion.encodeRequest
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit

object OptimizeServiceJs : OptimizeService {
  override suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
    val init = RequestInit(method = "POST", body = encodeRequest(request))
    val response = window.fetch("/v2/optimize", init).await()
    return response.takeIf { it.ok }?.let { decodeResponse(it.text().await()) }
      ?: throw Error("#optimize() call failed with status [${response.status}].")
  }
}

fun getOptimizeService(): OptimizeService = OptimizeServiceJs
