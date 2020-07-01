package ly.mens.exampleapp.services

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ly.mens.exampleapp.model.Restaurant

class FavouritesManager(val moshi: Moshi, val sharedPreferences: SharedPreferences) {
    companion object {
        const val KEY_FAVOURITES = "favourites"
    }

    private var favourites: Set<String> = emptySet()
    set(value) {
        field = value
        sharedPreferences.edit().putString(KEY_FAVOURITES, moshiAdapter.toJson(value.toList() ?: emptyList())).apply()
    }

    init {
        favourites = moshiAdapter.fromJson(sharedPreferences.getString(KEY_FAVOURITES, "[]")!!)!!.toSet()
    }

    private val moshiAdapter get() = moshi.adapter<List<String>>(
        Types.newParameterizedType(
        MutableList::class.java,
            String::class.java
    ))

    fun isFavourite(restaurant: Restaurant): Boolean = restaurant.id in favourites

    fun setFavourite(restaurant: Restaurant, favourite: Boolean) {
        if (favourite) {
            favourites = favourites + restaurant.id
        }
        else {
            favourites = favourites - restaurant.id
        }
    }
}