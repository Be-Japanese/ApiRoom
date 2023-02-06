package com.example.apiroom.mapper

import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.data.model.PostApiModel

class Apimapper:Mapper<postEntity, PostApiModel> {
    override fun modeltoentity(model: PostApiModel): postEntity {
        return postEntity(
            id = model.id,
            title = model.title,
            body = model.body,
          userId = model.userId
        )
    }

    override fun entitytomodel(entity: postEntity): PostApiModel {

        return PostApiModel(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            userId = entity.userId


        )    }


    fun modellisttoentitylist(apilist:List<PostApiModel>):List<postEntity>
    {
        return apilist.map { modeltoentity(it) }

    }
}