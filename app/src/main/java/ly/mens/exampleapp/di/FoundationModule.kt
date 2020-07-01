package ly.mens.exampleapp.di

import android.content.Context
import androidx.preference.PreferenceManager
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
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object FoundationModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext applicationContext: Context) = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .cache(Cache(applicationContext.cacheDir, 100 * 1024 * 1024))
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
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

    @Provides
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context) =
        PreferenceManager.getDefaultSharedPreferences(applicationContext)
}