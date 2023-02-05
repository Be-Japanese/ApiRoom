package com.example.apiroom.repository

import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.data.model.PostApiModel
import com.example.apiroom.data.model.PostDbModel
import com.example.apiroom.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {

     fun getsotries(): Flow<Resource<List<postEntity>>>
     suspend fun getsotriesfromdb(): Resource<List<PostDbModel>>


     suspend fun inserttopostdb( apilist : List<PostApiModel>):Boolean
}