package app.api.optimize.v1

import app.api.optimize.v1.OptimizeService.Companion.decodeResponse
import app.api.optimize.v1.OptimizeService.Companion.encodeRequest
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit
import react.FC
import react.PropsWithChildren
import react.createContext

object OptimizeServiceJs : OptimizeService {
  val Context = createContext<OptimizeService>()
  val Provider = FC<PropsWithChildren> { props ->
    Context.Provider(value = OptimizeServiceJs) {
      +props.children
    }
  }

  override suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
    val init = RequestInit(method = "POST", body = encodeRequest(request))
    val response = window.fetch("/v1/optimize", init).await()

    if (!response.ok) {
      throw Error("Optimize request failed with status [${response.status}]")
    }

    return decodeResponse(response.text().await())
  }
}
