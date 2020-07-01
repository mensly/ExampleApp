package ly.mens.exampleapp.services

import android.net.Uri
import ly.mens.exampleapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    companion object {
        private const val HEADER_API_KEY = "user-key"
    }
    val host = Uri.parse(BuildConfig.BASE_URL).host
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (chain.request().url().host() == host) {
            chain.proceed(
                chain.request().newBuilder().addHeader(HEADER_API_KEY, BuildConfig.API_KEY).build()
            )
        } else {
            chain.proceed(chain.request())
        }
    }

}