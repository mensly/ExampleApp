package ly.mens.exampleapp.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ly.mens.exampleapp.BuildConfig
import ly.mens.exampleapp.model.Restaurant

class RestaurantRepository(val zomatoApi: ZomatoApi) {
    companion object {
        private const val ENTITY_TYPE_SUBZONE = "subzone"
    }

    private var cache: List<Restaurant>? = null
        get() {
            if (field == null) {
                restoreCache()
            }
            return field
        }
        set(value) {
            field = value
            // TODO: Save to sharedpreferences
        }


    suspend fun getItems(): Flow<List<Restaurant>> = flow {
        cache?.let { emit(it) }
        val list = zomatoApi.search(
            BuildConfig.SUBZONE_ID_ABBOTSFORD,
            ENTITY_TYPE_SUBZONE,
            BuildConfig.LATITUDE,
            BuildConfig.LONGITUDE
        )
            .await().restaurants.map { it.restaurant }
        emit(list)
        cache = list
    }

    fun restoreCache() {
        // TODO: Load from sharedpreferences
    }
}