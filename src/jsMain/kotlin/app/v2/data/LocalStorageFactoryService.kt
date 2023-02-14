package app.v2.data

import app.serialization.AppJson
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class LocalStorageFactoryService : FactoryService {
    private val IDS_STORAGE = "//satisfactory.chichumunga.com/factories"

    private val ids: MutableList<ULong> by lazy {
        window.localStorage.getItem(IDS_STORAGE)?.split(",")?.map { it.toULong() }?.toMutableList() ?: mutableListOf()
    }

    override suspend fun createFactory(factory: Factory) = updateFactory(factory).also {
        ids.add(factory.id)
        window.localStorage.setItem(IDS_STORAGE, ids.joinToString(","))
    }

    override suspend fun getFactory(id: ULong): Factory =
        window.localStorage.getItem(storageName(id))?.also { lag() }?.let { AppJson.decodeFromString(it) }
            ?: throw Error("Factory [factories/$id] was not found.")

    override suspend fun updateFactory(factory: Factory) = factory.also { lag() }.also {
        window.localStorage.setItem(storageName(factory.id), AppJson.encodeToString(factory))
    }

    override suspend fun listFactories() = ids.map { getFactory(it) }

    private fun storageName(id: ULong) = "//satisfactory.chichumunga.com/factories/$id"
    private suspend fun lag() = delay(Random.nextInt(250, 1000).milliseconds)
}