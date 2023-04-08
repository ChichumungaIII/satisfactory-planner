package app.v2.common.layout

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

val DrawerContext = createContext<StateInstance<Boolean>>()

val DrawerContextProvider = FC<PropsWithChildren> { props ->
  val drawer = useState(true)
  DrawerContext(drawer) { +props.children }
}
