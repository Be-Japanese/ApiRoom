package com.example.apiroom.repository

import android.util.Log
import com.example.apiroom.Api.ApiService
import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.data.model.PostApiModel
import com.example.apiroom.data.model.PostDbModel
import com.example.apiroom.mapper.Apimapper
import com.example.apiroom.mapper.RoomApiMapper
import com.example.apiroom.mapper.RoomMapper
import com.example.apiroom.resource.Resource
import com.example.apiroom.room.PostDb
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepositoryImp : PostRepository {

    val Postdb = PostDb.instinc
    var apimapper = Apimapper()
    var roomapi = RoomApiMapper()
    var roommapre = RoomMapper()

    var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(ApiService::class.java)


    override suspend fun getsotriesfromdb(): Resource<List<PostDbModel>>  {
        if (Postdb is PostDb) {
            val posts = Postdb.storiosDao()
            try {
                var posts  = posts.getposts()
                return ( Resource.Successful(posts))
            } catch (e: Exception) {
                return Resource.Error(error = e)
            }
        } else
            return Resource.Error(Exception())
    }

    override suspend fun inserttopostdb(apilist: List<PostApiModel>): Boolean {

            if (Postdb is PostDb) {
                val posts = Postdb.storiosDao()
                var rr = roomapi.modellisttoentitylist(apilist)

                    for (s in rr)
                    {
                        var y = posts.insertAll(s)
                    }


                return true
            } else
                return false

    }

    override fun getsotries(): Flow<Resource<List<postEntity>>> = flow {

        emit(Resource.Loading)
        delay(1000)
        try {
            var lm = retrofit.getallposts()
            if (inserttopostdb(lm)) {
                emit(Resource.Inserted)
                delay(2000)
            }


            val result = getsotriesfromdb()


            if (result is Resource.Successful) {
                Log.w("okok",roommapre.modellisttoentitylist(result.data).toString())



                emit(Resource.Successful(roommapre.modellisttoentitylist(result.data)))
            }


        } catch (e: Exception) {
            emit(Resource.Error(error = e))
        }

    }


}