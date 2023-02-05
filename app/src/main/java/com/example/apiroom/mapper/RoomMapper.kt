package com.example.apiroom.mapper

import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.data.model.PostDbModel

class RoomMapper:Mapper<postEntity,PostDbModel> {
    override fun modeltoentity(model: PostDbModel): postEntity {

return postEntity(
    id = model.id,
    title = model.title,
    body = model.body,
    userId = model.userId)
    }

    override fun entitytomodel(entity: postEntity): PostDbModel {
        return PostDbModel(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            userId = entity.userId

        )    }

    fun modellisttoentitylist(apilist:List<PostDbModel>):List<postEntity>
    {
        return apilist.map { modeltoentity(it) }

    }
}