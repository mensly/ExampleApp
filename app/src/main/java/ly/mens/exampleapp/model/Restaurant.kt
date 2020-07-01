package ly.mens.exampleapp.model

import com.squareup.moshi.Json

data class Restaurant(
    val id: String,
    val name: String,
    val location: RestaurantLocation,
    @Json(name = "featured_image") val featuredImage: String
)

data class RestaurantLocation(val address: String)