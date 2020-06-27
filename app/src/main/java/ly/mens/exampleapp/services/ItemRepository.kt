package ly.mens.exampleapp.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.yield
import ly.mens.exampleapp.model.Item

private val SAMPLE_DATA = listOf(
    Item("1", "Item 1", "The first item"),
    Item("2", "Item 2", "The second item"),
    Item("3", "Item 3", "The last item")
)

class ItemRepository {
    suspend fun getItems(): Flow<List<Item>> = flow {
        // TODO: Emit cached data if available
        // TODO: Make http request
        emit(SAMPLE_DATA)
    }
}