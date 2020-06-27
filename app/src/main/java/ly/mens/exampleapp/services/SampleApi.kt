package ly.mens.exampleapp.services

import kotlinx.coroutines.Deferred
import ly.mens.exampleapp.model.Item
import retrofit2.http.GET

interface SampleApi {
    @GET("posts")
    fun listPosts(): Deferred<List<Item>>
}