package app.routes.plan

import app.api.plan.v1.Plan
import app.api.save.v1.Save
import app.redux.state.resource.plan.SavePlan
import app.redux.useAppDispatch
import app.routes.plan.partition.OptimizePartition
import app.routes.plan.partition.PartitionComponent
import react.FC
import react.Props

external interface PlanPageProps : Props {
  var save: Save
  var plan: Plan
}

val PlanPage = FC<PlanPageProps>("PlanPage") { props ->
  val dispatch = useAppDispatch()

  ProgressContext(props.save.progress) {
    PartitionContext(PartitionContextValue(props.plan.partition) { next ->
      val updated = next.copy(optimized = false)
      dispatch(SavePlan(props.plan.copy(partition = updated)))
      dispatch(OptimizePartition(updated) { optimized ->
        dispatch(SavePlan(props.plan.copy(partition = optimized)))
      })
    }) { PartitionComponent {} }
  }
}
