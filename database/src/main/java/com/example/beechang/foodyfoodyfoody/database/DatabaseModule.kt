package com.example.beechang.foodyfoodyfoody.database

import android.content.Context
import androidx.room.Room
import com.example.foodyfoody.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context ,
        typeResponseConverter: TypeResponseConverter
    ): RecipesDatabase {
        return Room
            .databaseBuilder(context, RecipesDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) = database.recipesDao()

}