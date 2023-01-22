package app.v2.data

import react.FC
import react.PropsWithChildren
import react.createContext

val FactoryServiceContext = createContext<FactoryService>()

val FactoryServiceContextComponent = FC<PropsWithChildren> { props ->
    FactoryServiceContext(InMemoryFactoryService()) { +props.children }
}
