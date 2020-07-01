package ly.mens.exampleapp.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ly.mens.exampleapp.BuildConfig
import ly.mens.exampleapp.services.ApiKeyInterceptor
import ly.mens.exampleapp.services.RestaurantRepository
import ly.mens.exampleapp.services.ZomatoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideSampleApi(okHttpClient: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create<ZomatoApi>()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
        }
        .build()

    @Provides
    @Singleton
    fun providePicasso(
        @ApplicationContext applicationContext: Context,
        okHttpClient: OkHttpClient
    ) = Picasso.Builder(applicationContext)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}