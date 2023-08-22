package app.common.layout.navigationlist

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

val NavigationListContext = createContext<StateInstance<Boolean>>()

val NavigationListContextProvider = FC<PropsWithChildren>("NavigationListContext") {
  val allSaves = useState(false)
  NavigationListContext.Provider {
    value = allSaves
    +it.children
  }
}
