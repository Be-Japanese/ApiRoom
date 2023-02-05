package com.example.apiroom.mapper

interface Mapper<Entity,Model> {

    fun modeltoentity(model:Model):Entity
    fun entitytomodel(entity: Entity):Model
}