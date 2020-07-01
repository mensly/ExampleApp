package ly.mens.exampleapp.model

data class Restaurant(val id: String, val name: String, val location: RestaurantLocation)

data class RestaurantLocation(val address: String)