package app.v2.data.service

import app.serialization.AppJson
import app.v2.data.Factory
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class LocalStorageFactoryService : FactoryService {
    private val IDS_STORAGE = "//satisfactory.chichumunga.com/factories"

    private val ids: MutableList<ULong> by lazy {
        window.localStorage.getItem(IDS_STORAGE)
            ?.takeUnless { it.isBlank() }
            ?.split(",")
            ?.map { it.toULong() }
            ?.toMutableList()
            ?: mutableListOf()
    }

    override suspend fun createFactory(factory: Factory) = updateFactory(factory).also {
        ids.add(factory.id)
        writeIds()
    }

    override suspend fun getFactory(id: ULong): Factory =
        window.localStorage.getItem(storageName(id))?.also { lag() }?.let { AppJson.decodeFromString(it) }
            ?: throw IllegalArgumentException("Factory [factories/$id] was not found.")

    override suspend fun updateFactory(factory: Factory) = factory.also { lag() }.also {
        window.localStorage.setItem(storageName(factory.id), AppJson.encodeToString(factory))
    }

    override suspend fun deleteFactory(id: ULong) {
        ids.remove(id)
        writeIds()
    }

    override suspend fun listFactories() = ids.map { getFactory(it) }

    private fun writeIds() {
        window.localStorage.setItem(IDS_STORAGE, ids.joinToString(","))
    }

    private fun storageName(id: ULong) = "//satisfactory.chichumunga.com/factories/$id"
    private suspend fun lag() = delay(Random.nextInt(250, 1000).milliseconds)
}