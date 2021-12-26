package com.task.nytimeaplpication.di


import com.task.nytimeaplpication.BuildConfig
import com.task.nytimeaplpication.networking.remote.EndPointService
import com.task.nytimeaplpication.networking.repository.ArticlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): EndPointService =
        retrofit.create(EndPointService::class.java)


    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(endPointService: EndPointService) =
        ArticlesRepositoryImpl(endPointService)



}
