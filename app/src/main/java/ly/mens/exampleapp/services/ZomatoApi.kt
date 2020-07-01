package ly.mens.exampleapp.services

import androidx.annotation.Keep
import kotlinx.coroutines.Deferred
import ly.mens.exampleapp.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoApi {
    @GET("search")
    fun search(
        @Query("entity_id") entityId: Int,
        @Query("entity_type") entityType: String,
        @Query("lat") latitude: Double,
        @Query("long") longitude: Double
    ): Deferred<SearchResponse>
}

@Keep
data class SearchResponseEntry(val restaurant: Restaurant)

@Keep
data class SearchResponse(val restaurants: List<SearchResponseEntry>)
