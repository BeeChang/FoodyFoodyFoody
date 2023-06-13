package com.example.beechang.foodyfoodyfoody.database

interface EntityMapper<Domain, Entity> {
    fun asEntity(domain: Domain): Entity
    fun asDomain(entity: Entity): Domain
}
