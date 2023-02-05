package com.example.apiroom.mapper

import com.example.apiroom.data.model.PostApiModel
import com.example.apiroom.data.model.PostDbModel

class RoomApiMapper:Mapper<PostApiModel,PostDbModel> {
    override fun modeltoentity(model: PostDbModel): PostApiModel {
        return PostApiModel(
            id = model.id,
            title = model.title,
            body = model.body,
            userId = model.userId

        )



    }

    override fun entitytomodel(entity: PostApiModel): PostDbModel {

       return PostDbModel(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            userId = entity.userId

        )

    }

    fun modellisttoentitylist(apilist:List<PostApiModel>):List<PostDbModel>
    {
        return apilist.map { entitytomodel(it) }

    }
}