package com.example.beechang.foodyfoodyfoody.database.mapper

interface EntityMapper<Domain, Entity> {
    fun asEntity(domain: Domain): Entity
    fun asDomain(entity: Entity): Domain
}
