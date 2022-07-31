package app.plan

import app.PlansContext
import app.model.Plan
import csstype.Padding
import csstype.px
import mui.lab.TabContext
import mui.lab.TabPanel
import mui.material.Box
import mui.material.Tab
import mui.material.Tabs
import mui.material.TabsVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import react.useState

external interface ProductionPlansProps : Props

val ProductionPlans = FC<ProductionPlansProps> {
    var plans by useContext(PlansContext)

    var selected by useState("new")

    Typography {
        sx {
            padding = Padding(12.px, 20.px)
        }

        variant = TypographyVariant.h2
        +"Production Plans"
    }

    TabContext {
        value = selected
        Box {
            Tabs {
                variant = TabsVariant.scrollable

                value = selected
                onChange = { _, next -> selected = next as String }

                plans.forEach { plan ->
                    Tab {
                        value = "${plan.id()}"
                        label = ReactNode(plan.title())
                    }
                }

                Tab {
                    value = "new"
                    onClick = {
                        val plan = Plan()
                        plans = plans + plan
                        selected = "${plan.id()}"
                    }
                    label = ReactNode("New Plan")
                }
            }
        }

        plans.forEach { plan ->
            TabPanel {
                value = "${plan.id()}"
                +plan.title()
                +" "
                +plan.id().toString()
            }
        }
    }
}
