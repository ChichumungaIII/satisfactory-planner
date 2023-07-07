package app.api.save.v1

import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SaveServiceLocalStorage : SaveService {
  private const val SAVES_STORAGE = "//satisfactory.chichumunga.com/v1/saves"

  private var saves: Map<SaveName, Save>
    get() =
      window.localStorage.getItem(SAVES_STORAGE)
        ?.let { Json.decodeFromString(it) }
        ?: mapOf()
    set(value) =
      window.localStorage.setItem(SAVES_STORAGE, Json.encodeToString(value))

  override suspend fun createSave(request: CreateSaveRequest): Save {
    val save = request.save
    if (saves.containsKey(save.name)) {
      throw Error("Save [${save.name.getResourceName()}] already exists.")
    }

    saves = saves + (save.name to save)
    return save
  }

  override suspend fun getSave(request: GetSaveRequest) =
    saves[request.name] ?: throw Error("Save [${request.name.getResourceName()}] does not exist.")

  override suspend fun updateSave(request: UpdateSaveRequest): Save {
    val existing = getSave(GetSaveRequest(request.save.name))

    val choose = { field: String ->
      request.save.takeIf { request.updateMask.contains(field) } ?: existing
    }
    val save = existing.copy(
      displayName = choose("displayName").displayName,
      phases = choose("phases").phases,
      tiers = choose("tiers").tiers,
      milestones = choose("milestones").milestones,
      research = choose("research").research,
    )

    saves = saves + (save.name to save)
    return save
  }

  override suspend fun deleteSave(request: DeleteSaveRequest) {
    getSave(GetSaveRequest(request.name))
    saves = saves - request.name
  }

  override suspend fun listSaves(request: ListSavesRequest): ListSavesResponse {
    val afterToken = saves.values.toList()
      .dropWhile { save -> request.pageToken?.let { it == save.name.getResourceName() } ?: false }
    val pageSize = request.pageSize ?: 100
    return ListSavesResponse(
      afterToken.take(pageSize),
      afterToken.drop(pageSize).firstOrNull()?.name?.getResourceName()
    )
  }
}
