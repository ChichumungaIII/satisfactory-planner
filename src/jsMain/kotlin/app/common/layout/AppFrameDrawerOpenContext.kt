package app.common.layout

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

val AppFrameDrawerOpenContext = createContext<StateInstance<Boolean>>()

val AppFrameDrawerOpenContextProvider = FC<PropsWithChildren>("AppFrameDrawerOpenContextProvider") {
  val drawerOpenState = useState(true)
  AppFrameDrawerOpenContext.Provider {
    value = drawerOpenState
    +it.children
  }
}
