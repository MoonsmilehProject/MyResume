package mx.com.moonsmileh.myresume.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mx.com.moonsmileh.myresume.data.response.JobResponse
import mx.com.moonsmileh.myresume.data.response.ProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val loggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

private val retrofit = Retrofit.Builder()
    .baseUrl("https://gist.githubusercontent.com/MoonsmilehProject/")
    .client(okHttpClient.build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface ResumeAPIService {

    @GET("2b69f9708a5e69c7e8dc69290c3f0191/raw/a1e5db6387e6ae4741338eff5272e8bd11f4385b/profile.json")
    fun fetchProfile(): Call<ProfileResponse>

    @GET("43fc07698d33848bf86d74491838b476/raw/30cd591d6b5308cc4643b76d66a0b597348948a1/resume.json")
    fun fetchJobs(): Call<JobResponse>
}

object ResumeAPI {
    val retrofitService: ResumeAPIService by lazy {
        retrofit.create(ResumeAPIService::class.java)
    }
}