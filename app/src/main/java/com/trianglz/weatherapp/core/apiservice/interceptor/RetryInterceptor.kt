package com.trianglz.weatherapp.core.apiservice.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor(private val maxRetryCount: Int, private val retryDelayMillis: Long) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())
        var retryCount = 0
        while (!response.isSuccessful && retryCount < maxRetryCount) {
            retryCount++
            Thread.sleep(retryDelayMillis)
            response = chain.proceed(chain.request())
        }
        return response
    }
}