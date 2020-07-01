package ly.mens.exampleapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ly.mens.exampleapp.BuildConfig
import ly.mens.exampleapp.services.ApiKeyInterceptor
import ly.mens.exampleapp.services.RestaurantRepository
import ly.mens.exampleapp.services.ZomatoApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ItemModule {
    @Provides
    @Singleton
    fun provideItemRepository(zomatoApi: ZomatoApi) = RestaurantRepository(zomatoApi)

    @Provides
    @Singleton
    fun provideSampleApi(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<ZomatoApi>()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()
}