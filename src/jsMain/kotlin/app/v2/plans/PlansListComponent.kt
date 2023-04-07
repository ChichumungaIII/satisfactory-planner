package app.v2.plans

import app.AppRoute
import app.v2.plans.data.model.Plan
import mui.icons.material.AccountTree
import mui.icons.material.Add
import mui.icons.material.Delete
import mui.material.Avatar
import mui.material.IconButton
import mui.material.ListItem
import mui.material.ListItemAvatar
import mui.material.ListItemButton
import mui.material.ListItemText
import mui.material.Typography
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import kotlin.random.Random
import kotlin.random.nextULong

external interface PlansListComponentProps : Props {
    var plans: List<Plan>
    var onCreate: (Plan) -> Unit
    var onDelete: (ULong) -> Unit
}

val PlansListComponent = FC<PlansListComponentProps>("PlansListComponent") { props ->
    val navigate = useNavigate()

    mui.material.List {
        props.plans.forEach { plan ->
            ListItem {
                disablePadding = true
                secondaryAction = IconButton.create {
                    Delete {}
                    onClick = { TODO() }
                }

                ListItemButton {
                    ListItemAvatar {
                        Avatar { AccountTree {} }
                    }
                    ListItemText { primary = Typography.create { +plan.title } }
                    onClick = { navigate(AppRoute.PLAN.url("planId" to "${plan.id}")) }

                }
            }
        }

        ListItem {
            disablePadding = true
            ListItemButton {
                ListItemAvatar { Avatar { Add {} } }
                ListItemText { primary = Typography.create { +"Create plan" } }
                onClick = { props.onCreate(Plan(Random.nextULong(), "New Plan")) }
            }
        }
    }
}
