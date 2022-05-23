package com.binar.ariefaryudisyidik.challengegoldchapter7.di

import androidx.room.Room
import com.binar.ariefaryudisyidik.challengegoldchapter7.data.UserRepository
import com.binar.ariefaryudisyidik.challengegoldchapter7.data.local.UserRoomDatabase
import com.binar.ariefaryudisyidik.challengegoldchapter7.data.remote.retrofit.ApiService
import com.binar.ariefaryudisyidik.challengegoldchapter7.utils.UserDataStoreManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<UserRoomDatabase>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserRoomDatabase::class.java,
            "db_user"
        ).allowMainThreadQueries().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        UserRepository(get())
    }
}

val storageModule = module {
    single {
        UserDataStoreManager(get())
    }
}