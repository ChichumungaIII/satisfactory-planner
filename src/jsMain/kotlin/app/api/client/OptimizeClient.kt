package app.api.client

import app.api.OptimizeRequest
import app.api.OptimizeResponse
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit

suspend fun optimize(request: OptimizeRequest) = coroutineScope {
    val init = RequestInit(method = "POST", body = Json.encodeToString(request))
    val response = window.fetch("/v1/optimize", init).await()

    if (response.ok) Json.decodeFromString<OptimizeResponse>(response.text().await())
    else throw Error("Optimize request failed with status [${response.status}]")
}
