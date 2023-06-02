package app.v2.api.factory

import react.FC
import react.PropsWithChildren
import react.createContext

val FactoryServiceContext = createContext<FactoryService>()

val FactoryServiceContextProvider = FC<PropsWithChildren> { props ->
  FactoryServiceContext(LocalStorageFactoryService()) { +props.children }
}
