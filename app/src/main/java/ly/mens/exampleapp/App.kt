package ly.mens.exampleapp

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject lateinit var picasso: Picasso

    override fun onCreate() {
        super.onCreate()
        Picasso.setSingletonInstance(picasso)
    }
}