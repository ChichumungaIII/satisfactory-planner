package app

import csstype.px
import mui.lab.TabContext
import mui.lab.TabPanel
import mui.material.AppBar
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
import react.useState

external interface AppProps: Props

val App = FC<AppProps> { _ ->
    var tab by useState("0")

    AppBar {
        Typography {
            variant = TypographyVariant.h1
            +"Satisfactory Planner"
        }
    }

    TabContext {
        value = tab

        Box {
            sx {
                marginTop = 112.px
            }

            Tabs {
                variant = TabsVariant.fullWidth
                value = tab
                onChange = { _, next -> tab = next as String }

                Tab {
                    value = "0"

                    label = ReactNode("Production Plans")
                }
                Tab {
                    value = "1"

                    label = ReactNode("Factories")
                }
            }
        }

        TabPanel {
            value = "0"

            ProductionPlans {}
        }
        TabPanel {
            value = "1"

            Factories {}
        }
    }
}
