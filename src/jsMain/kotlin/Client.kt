import app.Routing
import kotlinx.browser.document
import react.create
import react.dom.client.createRoot
import web.dom.Element

fun main() {
  val container = document.createElement("div")
  document.body!!.appendChild(container)
  createRoot(container.unsafeCast<Element>()).render(Routing.create())
}
