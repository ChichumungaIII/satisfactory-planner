package app.api.save.v1

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.random.Random

@Serializable(with = SaveNameSerializer::class)
data class SaveName(val id: Int) {
  companion object {
    fun createRandom() = SaveName(Random.nextInt())

    private val PATTERN = Regex("""saves/(\d+)""")
    fun parse(name: String) =
      PATTERN.matchEntire(name)?.let {
        SaveName(it.groups[1]!!.value.toUInt().toInt())
      } ?: throw IllegalArgumentException("[$name] is not a valid Save name.")
  }

  fun getResourceName() = "saves/${id.toUInt()}"
}

object SaveNameSerializer : KSerializer<SaveName> {
  override val descriptor = PrimitiveSerialDescriptor("SaveName", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: SaveName) =
    encoder.encodeString(value.getResourceName())

  override fun deserialize(decoder: Decoder) = SaveName.parse(decoder.decodeString())
}
