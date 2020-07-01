package ly.mens.exampleapp.services

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ly.mens.exampleapp.BuildConfig
import ly.mens.exampleapp.model.Restaurant
import java.io.IOException


class RestaurantRepository(val zomatoApi: ZomatoApi, val moshi: Moshi, val sharedPreferences: SharedPreferences) {
    companion object {
        private const val ENTITY_TYPE_SUBZONE = "subzone"
        private const val KEY_RESTAURANT_CACHE = "restaurants"
    }

    private var cache: List<Restaurant>? = null
        get() {
            if (field == null) {
                sharedPreferences.getString(KEY_RESTAURANT_CACHE, null)?.let {
                    field = moshiAdapter.fromJson(it)
                }
            }
            return field
        }
        set(value) {
            field = value
            sharedPreferences.edit().putString(KEY_RESTAURANT_CACHE, moshiAdapter.toJson(value)).apply()
        }

    private val moshiAdapter get() = moshi.adapter<List<Restaurant>>(Types.newParameterizedType(
            MutableList::class.java,
            Restaurant::class.java
        ))

    suspend fun getItems(): Flow<List<Restaurant>> = flow {
        cache?.let { emit(it) }
        try {
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
        catch (e: IOException) {
            if (cache == null) {
                throw e
            }
        }
    }
}