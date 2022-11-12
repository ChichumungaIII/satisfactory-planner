package app.factory

import app.factory.model.Factory
import csstype.Display
import csstype.FlexDirection
import csstype.Padding
import csstype.px
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mui.icons.material.Add
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
import react.create
import react.useEffect
import react.useState

external interface FactoriesComponentProps : Props

const val NEW_FACTORY_ID = "new"

const val FACTORIES_STORAGE = "com.chichumunga.satisfactory::factories"
fun save(plans: List<Factory>) = window.localStorage.setItem(FACTORIES_STORAGE, Json.encodeToString(plans))
fun load(): List<Factory>? = window.localStorage.getItem(FACTORIES_STORAGE)?.let { Json.decodeFromString(it) }

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { props ->
    var factories by useState(load() ?: listOf())
    var selected by useState(if (factories.isEmpty()) NEW_FACTORY_ID else "${factories[0].id}")

    useEffect { save(factories) }

    TabContext {
        value = selected
        Tabs {
            variant = TabsVariant.scrollable

            value = selected
            onChange = { _, next -> selected = next as String }

            factories.forEach { factory ->
                Tab {
                    sx { width = 192.px }
                    wrapped = true

                    value = "${factory.id}"
                    label = ReactNode(factory.title)
                }
            }

            Tab {
                sx { flexDirection = FlexDirection.row }

                value = NEW_FACTORY_ID
                onClick = {
                    val factory = Factory()
                    factories = factories + factory
                    selected = "${factory.id}"
                }
                label = Box.create {
                    sx { display = Display.contents }

                    Add { sx { marginTop = (-2).px } }
                    +"New Factory"
                }
            }
        }

        factories.forEachIndexed { i, factory ->
            TabPanel {
                sx { padding = Padding(0.px, 0.px) }
                value = "${factory.id}"

                Typography {
                    variant = TypographyVariant.h2
                    +factory.title
                }
            }
        }
    }
}
