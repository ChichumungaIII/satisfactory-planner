package app.plan

import app.Header
import app.model.PlanModel
import csstype.Display
import csstype.FlexDirection
import csstype.Padding
import csstype.px
import mui.icons.material.Add
import mui.lab.TabContext
import mui.lab.TabPanel
import mui.material.Box
import mui.material.Tab
import mui.material.Tabs
import mui.material.TabsVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useState

external interface PlansProps : Props

val Plans = FC<PlansProps>("Plans") {
    var plans by useState(listOf<PlanModel>())
    var selected by useState("new")

    Header {
        title = "Production Plans"

        TabContext {
            value = selected
            Box {
                Tabs {
                    variant = TabsVariant.scrollable

                    value = selected
                    onChange = { _, next -> selected = next as String }

                    plans.forEach { plan ->
                        Tab {
                            sx { width = 192.px }
                            wrapped = true

                            value = "${plan.id()}"
                            label = ReactNode(plan.title())
                        }
                    }

                    Tab {
                        sx {
                            flexDirection = FlexDirection.row
                        }

                        value = "new"
                        onClick = {
                            val plan = PlanModel()
                            plans = plans + plan
                            selected = "${plan.id()}"
                        }
                        label = Box.create {
                            sx { display = Display.contents }

                            Add { sx { marginTop = (-2).px } }
                            +"New Plan"
                        }
                    }
                }
            }

            plans.withIndex().forEach { (i, plan) ->
                TabPanel {
                    sx { padding = Padding(0.px, 0.px) }
                    value = "${plan.id()}"

                    Plan {
                        this.plan = plan
                        setPlan = { newPlan ->
                            plans = plans.subList(0, i) + newPlan + plans.subList(i + 1, plans.size)
                        }
                    }
                }
            }
        }
    }
}
