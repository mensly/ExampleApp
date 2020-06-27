package ly.mens.exampleapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ly.mens.exampleapp.services.ItemRepository

@Module
@InstallIn(ApplicationComponent::class)
object ItemModule {
    @Provides
    fun provideItemRepository() = ItemRepository()
}