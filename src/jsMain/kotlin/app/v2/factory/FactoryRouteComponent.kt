package app.v2.factory

import app.AppRoute
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.LoadState.Failure
import app.v2.data.LoadState.Loaded
import app.v2.data.LoadState.Loading
import csstype.ClassName
import js.core.get
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.CircularProgress
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.PropsWithChildren
import react.create
import react.router.useNavigate
import react.router.useParams
import react.useContext
import react.useEffectOnce

external interface FactoryRouteComponentProps : Props

val FactoryRouteComponent = FC<FactoryRouteComponentProps>("FactoryRouteComponent") { _ ->
  val factoryId = useParams()["factoryId"]!!.toULong()
  val navigate = useNavigate()
  val (factory, updateFactory) = useContext(FactoryContext)
  useEffectOnce { updateFactory(SetFactoryId(factoryId)) }

  when (factory) {
    is Loading -> FactoryNotYetLoadedComponent {
      ZeroStateComponent {
        CircularProgress {}
      }
    }

    is Loaded -> FactoryComponent {
      this.factory = factory.data
      setFactory = { next -> updateFactory(SaveFactory(next)) }
    }

    is Failure -> FactoryNotYetLoadedComponent {
      ZeroStateComponent {
        Typography {
          variant = TypographyVariant.subtitle1
          +factory.message
        }
        Button {
          className = ClassName("factory-route__return-button")

          variant = ButtonVariant.contained
          +"Factory List"

          onClick = { navigate.invoke(AppRoute.FACTORIES.url) }
        }
      }
    }
  }
}


private val FactoryNotYetLoadedComponent = FC<PropsWithChildren>("FactoryNotYetLoadedComponent") { props ->
  FrameComponent {
    titleBar = TitleBarComponent.create {
      title = AppTitleComponent.create { title = "Factories" }
    }
    +props.children
  }
}
