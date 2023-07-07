package app.api.save.v1

import kotlinx.serialization.Serializable

interface SaveService {
  suspend fun createSave(request: CreateSaveRequest): Save
  suspend fun getSave(request: GetSaveRequest): Save
  suspend fun updateSave(request: UpdateSaveRequest): Save
  suspend fun deleteSave(request: DeleteSaveRequest)
  suspend fun listSaves(request: ListSavesRequest): ListSavesResponse
}

@Serializable
data class CreateSaveRequest(
  val save: Save
)

@Serializable
data class GetSaveRequest(
  val name: SaveName
)

@Serializable
data class UpdateSaveRequest(
  val save: Save,
  val updateMask: List<String>,
)

@Serializable
data class DeleteSaveRequest(
  val name: SaveName
)

@Serializable
data class ListSavesRequest(
  val pageSize: Int? = null,
  val pageToken: String? = null,
)

@Serializable
data class ListSavesResponse(
  val saves: List<Save>,
  val nextPageToken: String? = null,
)
