package app.routes.plan

import app.api.plan.v1.Plan.Partition
import react.createContext
import react.useContext
import kotlin.reflect.KProperty

data class PartitionContextValue(
  val partition: Partition,
  val setPartition: (Partition) -> Unit,
) {
  operator fun getValue(thisRef: Any?, property: KProperty<*>) = partition
  operator fun setValue(thisRef: Any?, property: KProperty<*>, next: Partition) = setPartition(next)
}

val PartitionContext = createContext<PartitionContextValue>()
fun usePartition() = useContext(PartitionContext)
  ?: throw Error("Cannot call `usePartition` outside of a Partition context.")
