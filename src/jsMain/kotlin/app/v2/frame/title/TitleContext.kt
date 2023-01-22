package app.v2.frame.title

import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

val TitleContext = createContext<StateInstance<String>>()

val TitleContextComponent = FC<PropsWithChildren> { props ->
    val state = useState("Satisfactory Planner")
    TitleContext(state) { +props.children }
}
