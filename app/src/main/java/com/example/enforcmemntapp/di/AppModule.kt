package com.example.enforcmemntapp.di

import android.content.Context
import androidx.room.Room
import com.example.enforcmemntapp.data.source.local.EnforcementDatabase
import com.example.enforcmemntapp.data.source.local.dao.UserdataDao
import com.example.enforcmemntapp.data.source.remote.EnforcementApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesEnforcementApi(): EnforcementApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(EnforcementApi.BASE_url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun providesOnboardingDb(@ApplicationContext context: Context): EnforcementDatabase {
        return Room.databaseBuilder(
            context,
            EnforcementDatabase::class.java,
            "enforcement_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserData(enforcementDatabase: EnforcementDatabase):UserdataDao{
       return enforcementDatabase.UserdataDao()
    }
}