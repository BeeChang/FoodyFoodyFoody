package com.example.beechang.foodyfoodyfoody.data.di


import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepository
import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepositoryImpl
import com.example.beechang.foodyfoodyfoody.data.repository.RecipesRepository
import com.example.beechang.foodyfoodyfoody.data.repository.RecipesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

    @Binds
    fun bindsFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

}