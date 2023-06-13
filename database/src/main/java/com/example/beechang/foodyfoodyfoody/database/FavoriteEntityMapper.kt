package com.example.beechang.foodyfoodyfoody.database

import com.example.beechang.foodyfoodyfoody.model.Favorites

object FavoriteEntityMapper : EntityMapper<Favorites, FavoritesEntity> {

    override fun asEntity(domain: Favorites): FavoritesEntity =
        FavoritesEntity(domain.id, domain.result)


    override fun asDomain(entity: FavoritesEntity): Favorites =
        Favorites(entity.id, entity.result)
}

fun Favorites.asEntity(): FavoritesEntity = FavoriteEntityMapper.asEntity(this)

fun FavoritesEntity.asDomain(): Favorites = FavoriteEntityMapper.asDomain(this)
