package kr.pjs.booksearch.data.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kr.pjs.booksearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit manager 클래스
 * retrofit configuration 및 객체를 관리한다.
 */
class RetrofitManager private constructor() {

    private var retrofit: Retrofit? = null
    private val httpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
    }

    /**
     * retrofit 객체 가져오기
     */
    fun retrofit(): Retrofit {
        if (retrofit == null) {
            build(baseUrl)
        }
        return retrofit!!
    }

    /**
     * retrofit build 하기
     */
    fun build(url: String) {
        retrofit = builder(url).build()
    }

    /**
     * retrofit builder 설정하기
     * 로그 인터셉터와 헤더 인터셉터 추가
     */
    fun builder(url: String): Retrofit.Builder {
        if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logInterceptor)
        }

        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK $kakaoRestApiKey")
                .build()
            return@Interceptor it.proceed(request)
        }
        httpClientBuilder.addInterceptor(headerInterceptor)

        return Retrofit
            .Builder()
            .baseUrl(url)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    companion object {
        @Volatile
        private var instance: RetrofitManager? = null

        @JvmStatic
        fun create(): RetrofitManager =
            instance ?: synchronized(this) {
                instance ?: RetrofitManager().also {
                    instance = it
                }
            }
    }
}