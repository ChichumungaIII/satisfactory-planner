package app.v2.frame

import react.FC
import react.PropsWithChildren
import react.ReactNode
import react.StateInstance
import react.createContext
import react.useState

val FrameMenuOptionsContext = createContext<StateInstance<List<ReactNode>>>()

val FrameMenuOptionsContextComponent = FC<PropsWithChildren> { props ->
    val state = useState<List<ReactNode>>(listOf())
    FrameMenuOptionsContext(state) { +props.children }
}
