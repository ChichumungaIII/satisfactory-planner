package app.common.layout.appframe

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

data class AppFrameState(
  val drawerOpen: Boolean = true,
) {
  companion object {
    val Context = createContext<StateInstance<AppFrameState>>()
    val Provider = FC<PropsWithChildren>("AppFrameState") {
      val state = useState(AppFrameState())
      Context(state) { +it.children }
    }
  }
}