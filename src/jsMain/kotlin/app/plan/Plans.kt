package app.plan

import app.model.PlanModel
import app.serialization.AppJson
import csstype.Display
import csstype.FlexDirection
import csstype.Padding
import csstype.px
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
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
import react.useEffect
import react.useState
import kotlin.math.min

const val NEW_PLAN_ID = "new"

external interface PlansProps : Props

const val PLANS_STORAGE = "com.chichumunga.satisfactory::plans"
fun save(plans: List<PlanModel>) = window.localStorage.setItem(PLANS_STORAGE, AppJson.encodeToString(plans))
fun load(): List<PlanModel>? = window.localStorage.getItem(PLANS_STORAGE)?.let { AppJson.decodeFromString(it) }

val Plans = FC<PlansProps>("Plans") {
  var plans by useState(load() ?: listOf())
  var selected by useState(if (plans.isEmpty()) NEW_PLAN_ID else "${plans[0].id}")

  useEffect { save(plans) }

  TabContext {
    value = selected
    Tabs {
      variant = TabsVariant.scrollable

      value = selected
      onChange = { _, next -> selected = next as String }

      plans.forEach { plan ->
        Tab {
          sx { width = 192.px }
          wrapped = true

          value = "${plan.id}"
          label = ReactNode(plan.title)
        }
      }

      Tab {
        sx {
          flexDirection = FlexDirection.row
        }

        value = NEW_PLAN_ID
        onClick = {
          val plan = PlanModel()
          plans = plans + plan
          selected = "${plan.id}"
        }
        label = Box.create {
          sx { display = Display.contents }

          Add { sx { marginTop = (-2).px } }
          +"New Plan"
        }
      }
    }

    plans.withIndex().forEach { (i, plan) ->
      TabPanel {
        sx { padding = Padding(0.px, 0.px) }
        value = "${plan.id}"

        Plan {
          this.plan = plan
          this.setPlan = { newPlan ->
            plans = plans.subList(0, i) + newPlan + plans.subList(i + 1, plans.size)
          }
          this.onDelete = {
            val next = plans.subList(0, i) + plans.subList(i + 1, plans.size)
            plans = next
            selected = if (next.isNotEmpty()) "${next[min(i, next.size - 1)].id}" else NEW_PLAN_ID
          }
        }
      }
    }
  }
}
