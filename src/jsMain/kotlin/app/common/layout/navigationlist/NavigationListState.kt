package app.common.layout.navigationlist

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

data class NavigationListState(
  val showAllSaves: Boolean = false,
) {
  companion object {
    val Context = createContext<StateInstance<NavigationListState>>()
    val Provider = FC<PropsWithChildren>("NavigationListState") {
      val state = useState(NavigationListState())
      Context(state) { +it.children }
    }
  }
}
