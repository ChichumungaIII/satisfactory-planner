package app.routes.save

import app.api.save.v1.Save
import app.redux.AppState
import app.redux.Decrement
import app.redux.Increment
import app.redux.RThunk
import app.redux.useAppDispatch
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.redux.useSelector
import react.useContext
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.seconds

external interface SavePageProps : Props {
  var save: Save
}

val SavePage = FC<SavePageProps>("SavePage") { props ->
  val counter = useSelector(AppState::counter)
  val dispatch = useAppDispatch()
  println(counter)

  var state by useContext(SavePageState.Manager.Context)!!.scope(props.save.name)

  Accordion {
    expanded = state.showPlans
    onChange = { _, next ->
      state = state.copy(showPlans = next)
      dispatch(Increment)

      dispatch(object : RThunk {
        override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
          launchMain {
            delay(2.seconds)
            dispatch(Decrement)
          }
        }
      })
    }

    AccordionSummary {
      expandIcon = ExpandMore.create()

      Typography {
        variant = TypographyVariant.h2
        +"Plans"
      }
    }

    AccordionDetails {
      +"Plans"
    }
  }
}
