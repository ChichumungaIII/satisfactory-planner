package app.v2.factory

import app.v2.common.layout.ZeroStateComponent
import app.v2.data.Failure
import app.v2.data.Loaded
import app.v2.data.Loading
import csstype.ClassName
import kotlinx.coroutines.MainScope
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
import react.useEffect

private val Scope = MainScope()

external interface FactoryRouteComponentProps : Props

val FactoryRouteComponent = FC<FactoryRouteComponentProps>("FactoryRouteComponent") { props ->
    val (factory, updateFactory) = useContext(FactoryContext)
    // TODO: Embed this in routing and expose a typed route params hook
    val factoryId = useParams()["factoryId"]!!.toULong()
    useEffect(factoryId) { updateFactory(SetFactoryId(factoryId)) }

    val navigate = useNavigate()

    when (factory) {
        is Loaded -> FactoryComponent { this.factory = factory.value }
        is Loading -> ZeroStateComponent { CircularProgress {} }
        is Failure -> ZeroStateComponent {
            Typography {
                variant = TypographyVariant.subtitle1
                +factory.message
            }
            Button {
                className = ClassName("factory-route__return-button")

                variant = ButtonVariant.contained
                +"Factory List"

                onClick = { navigate("..") }
            }
        }
    }
}
