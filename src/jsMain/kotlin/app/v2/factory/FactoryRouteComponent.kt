package app.v2.factory

import app.v2.common.layout.ZeroStateComponent
import app.v2.data.LoadState
import csstype.ClassName
import js.core.get
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.CircularProgress
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.router.useNavigate
import react.router.useParams
import react.useContext

external interface FactoryRouteComponentProps : Props

val FactoryRouteComponent = FC<FactoryRouteComponentProps>("FactoryRouteComponent") { _ ->
    val factoryId = useParams()["factoryId"]!!.toULong()
    val navigate = useNavigate()
    val (factory, updateFactory) = useContext(FactoryContext)

    when (factory) {
        is LoadState.Loaded -> FactoryComponent {
            this.factory = factory.data
            setFactory = { next -> updateFactory(SaveFactory(next)) }
        }

        is LoadState.Loading -> ZeroStateComponent { CircularProgress {} }
        is LoadState.Failure -> ZeroStateComponent {
            Typography {
                variant = TypographyVariant.subtitle1
                +factory.message
            }
            Button {
                className = ClassName("factory-route__return-button")

                variant = ButtonVariant.contained
                +"Factory List"

                onClick = { navigate.invoke("..") }
            }
        }

        else -> updateFactory(SetFactoryId(factoryId))
    }
}
