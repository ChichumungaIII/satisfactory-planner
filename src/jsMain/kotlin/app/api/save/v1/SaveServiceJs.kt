package app.api.save.v1

import react.FC
import react.PropsWithChildren
import react.createContext

object SaveServiceJs {
  val Context = createContext<SaveService>()
  val Provider = FC<PropsWithChildren> { props ->
    Context.Provider(value = SaveServiceLocalStorage) {
      +props.children
    }
  }
}
