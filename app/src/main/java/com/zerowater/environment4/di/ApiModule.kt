package com.zerowater.environment4.di

import android.content.Context
import com.zerowater.data.remote.NetworkService
import com.zerowater.environment4.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    /**
     * 캐시 시간
     */
    private const val CACHE_MAX_AGE_MINUTE: Int = 5

    /**
     * 캐시 사이즈
     */
    private const val CACHE_SIZE = 10 * 1024 * 1024L

    /**
     * 네트워크 Dispatcher 이름
     */
    private const val DISPATCHER_NAME = "apiDispatcher"

    /**
     * 캐시 인터셉터 이름
     */
    private const val CACHE_INTERCEPTOR_NAME = "cacheInterceptor"

    /**
     * 로그 인터셉터 이름
     */
    private const val LOGGING_INTERCEPTOR_NAME = "loggingInterceptor"

    /**
     * Cache-Control 키
     */
    private const val HEADER_CACHE_CONTROL = "Cache-Control"

    /**
     * PRAGMA 키
     */
    private const val HEADER_PRAGMA = "Pragma"

    /**
     * Authorization 키
     */
    private const val HEADER_AUTHORIZATION = "Authorization"

    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    /**
     * Provide io dispatcher
     *
     */
    @Singleton
    @Provides
    @Named(DISPATCHER_NAME)
    fun provideIoDispatcher() = Dispatchers.IO


    /**
     * Retrofit 제공자
     *
     * @param gson         Gson
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create())
            baseUrl(BuildConfig.BASE_URL)
            client(okHttpClient)
        }.build().create(NetworkService::class.java)
    }

    /**
     * OkHttpClient 제공자
     *
     * @param cache  캐시
     * @param httpLoggingInterceptor  로그 Interceptor
     * @param cacheInterceptor       캐시 Interceptor
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        @Named(LOGGING_INTERCEPTOR_NAME) httpLoggingInterceptor: Interceptor,
        @Named(CACHE_INTERCEPTOR_NAME) cacheInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            addInterceptor(httpLoggingInterceptor)
            addNetworkInterceptor(cacheInterceptor)
        }.build()
    }

    /**
     * 캐시 Interceptor 제공자
     *
     * @return 캐시 Interceptor
     */
    @Singleton
    @Provides
    @Named(CACHE_INTERCEPTOR_NAME)
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder().apply {
                maxAge(CACHE_MAX_AGE_MINUTE, TimeUnit.MINUTES)
            }.build()

            response.newBuilder().apply {
                removeHeader(HEADER_PRAGMA)
                removeHeader(HEADER_CACHE_CONTROL)
                header(HEADER_CACHE_CONTROL, cacheControl.toString())
            }.build()
        }
    }

    /**
     * 로그 Interceptor 제공자
     *
     * @return 로그 Interceptor
     */
    @Singleton
    @Provides
    @Named(LOGGING_INTERCEPTOR_NAME)
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }


    /**
     * Cache 제공자
     *
     * @return Cache
     */
    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }


}