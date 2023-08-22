package app.routes.save

import app.api.save.v1.SaveName
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.createContext
import react.useState
import kotlin.reflect.KProperty

data class SavePageState(
  val showPlans: Boolean = false,
) {
  data class Manager(
    private val scopes: Map<SaveName, SavePageState>,
    private val setScopes: StateSetter<Map<SaveName, SavePageState>>,
  ) {
    companion object {
      val Context = createContext<Manager>()
      val Provider = FC<PropsWithChildren>("SavePageStateManager") {
        val (scopes, setScopes) = useState(mapOf<SaveName, SavePageState>())
        Context(Manager(scopes, setScopes)) { +it.children }
      }
    }

    fun scope(save: SaveName) = Delegate(save)

    inner class Delegate internal constructor(val save: SaveName) {
      operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        scopes[save] ?: SavePageState()

      operator fun setValue(thisRef: Any?, property: KProperty<*>, state: SavePageState) =
        setScopes(scopes + (save to state))
    }
  }
}
