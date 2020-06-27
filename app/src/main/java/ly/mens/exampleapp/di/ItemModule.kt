package ly.mens.exampleapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ly.mens.exampleapp.BuildConfig
import ly.mens.exampleapp.services.ItemRepository
import ly.mens.exampleapp.services.SampleApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ItemModule {
    @Provides
    @Singleton
    fun provideItemRepository(sampleApi: SampleApi) = ItemRepository(sampleApi)

    @Provides
    @Singleton
    fun provideSampleApi() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<SampleApi>()
}