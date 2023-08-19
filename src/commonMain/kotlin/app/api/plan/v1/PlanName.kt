package app.api.plan.v1

import app.api.common.ResourceName
import app.api.save.v1.SaveName
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.random.Random

@Serializable(with = PlanNameSerializer::class)
data class PlanName(val save: SaveName, val id: Int) : ResourceName {
  companion object {
    fun createRandom(save: SaveName) = PlanName(save, Random.nextInt())

    private val PATTERN = Regex("""(.+)/plans/(\d+)""")
    fun parse(name: String) =
      PATTERN.matchEntire(name)?.let {
        PlanName(SaveName.parse(it.groups[1]!!.value), it.groups[2]!!.value.toUInt().toInt())
      } ?: throw IllegalArgumentException("[$name] is not a valid Plan name.")
  }

  override fun getResourceName() = "${save.getResourceName()}/plans/${id.toUInt()}"
}

object PlanNameSerializer : KSerializer<PlanName> {
  override val descriptor = PrimitiveSerialDescriptor("PlanName", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: PlanName) =
    encoder.encodeString(value.getResourceName())

  override fun deserialize(decoder: Decoder) = PlanName.parse(decoder.decodeString())
}
