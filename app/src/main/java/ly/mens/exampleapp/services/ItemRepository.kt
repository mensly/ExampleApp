package ly.mens.exampleapp.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ly.mens.exampleapp.model.Item

class ItemRepository(val sampleApi: SampleApi) {
    private var cache: List<Item>? = null
    suspend fun getItems(): Flow<List<Item>> = flow {
        cache?.let { emit(it) }
        val list = sampleApi.listPosts().await()
        emit(list)
        cache = list
    }
}