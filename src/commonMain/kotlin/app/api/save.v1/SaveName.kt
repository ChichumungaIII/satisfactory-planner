package app.api.save.v1

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SaveNameSerializer::class)
data class SaveName(val id: Int)

private object SaveNameSerializer : KSerializer<SaveName> {
  override val descriptor = PrimitiveSerialDescriptor("SaveName", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: SaveName) =
    encoder.encodeString("saves/${value.id.toUInt()}")

  override fun deserialize(decoder: Decoder) =
    SaveName(decoder.decodeString().substringAfter('/').toUInt().toInt())
}
