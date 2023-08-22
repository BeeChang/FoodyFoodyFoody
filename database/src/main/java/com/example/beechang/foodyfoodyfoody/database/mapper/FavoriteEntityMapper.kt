package com.example.beechang.foodyfoodyfoody.database.mapper

import com.example.beechang.foodyfoodyfoody.database.entity.FavoritesEntity
import com.example.beechang.foodyfoodyfoody.model.Favorites

object FavoriteEntityMapper : EntityMapper<Favorites, FavoritesEntity> {

    override fun asEntity(domain: Favorites): FavoritesEntity =
        FavoritesEntity(domain.id, domain.foodyResult)


    override fun asDomain(entity: FavoritesEntity): Favorites =
        Favorites(entity.id, entity.foodyResult)
}

fun Favorites.asEntity(): FavoritesEntity = FavoriteEntityMapper.asEntity(this)

fun FavoritesEntity.asDomain(): Favorites = FavoriteEntityMapper.asDomain(this)
