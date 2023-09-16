package app.v2.factories

import app.AppRoute
import app.v2.AppScope
import app.v2.api.factory.FactoryServiceContext
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.Factory
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactory
import kotlinx.coroutines.launch
import mui.material.Backdrop
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.CircularProgress
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState
import web.cssom.ClassName
import kotlin.random.Random
import kotlin.random.nextULong

external interface FactoriesComponentProps : Props

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { _ ->
  val (factories, updateFactories) = useContext(FactoriesContext)!!

  FrameComponent {
    titleBar = TitleBarComponent.create {
      title = AppTitleComponent.create { title = "Factories" }
    }
    when (factories) {
      is LoadState.Loading -> ZeroStateComponent {
        CircularProgress { size = 80; thickness = 4.8 }
      }

      is LoadState.Loaded -> when (factories.data.size) {
        0 -> ZeroFactoriesPlaceholderComponent { }
        else -> FactoriesListComponent {
          this.factories = factories.data
        }
      }

      else -> null.also { updateFactories(LoadList) }
    }
  }
}

private val ZeroFactoriesPlaceholderComponent = FC<Props>("ZeroFactoriesPlaceholderComponent") { _ ->
  val navigate = useNavigate()
  val factoryService = useContext(FactoryServiceContext)!!
  val (_, updateStore) = useContext(FactoryStoreContext)!!
  val (_, updateFactories) = useContext(FactoriesContext)!!

  var creating by useState(false)

  ZeroStateComponent {
    Typography {
      variant = TypographyVariant.subtitle1
      +"It doesn't look like you've made any factories yet."
    }
    Button {
      className = ClassName("factories__create-button")

      variant = ButtonVariant.contained
      disabled = creating
      +"Create a factory"

      onClick = {
        creating = true
        AppScope.launch {
          val factory = Factory(Random.nextULong(), "New Factory")
          updateStore(SetFactory(factoryService.createFactory(factory)))
          updateFactories(AddFactory(factory))
          navigate.invoke(AppRoute.V2_FACTORY.url("factoryId" to "${factory.id}"))
        }
      }
    }
  }

  Backdrop {
    open = creating
    CircularProgress { size = 80; thickness = 4.8 }
  }
}
