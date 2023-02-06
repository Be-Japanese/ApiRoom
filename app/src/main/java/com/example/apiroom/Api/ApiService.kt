package com.example.apiroom.Api

import com.example.apiroom.data.model.PostApiModel
import retrofit2.http.GET

interface ApiService {
    @GET(value="/posts")
    suspend fun getallposts(): List<PostApiModel>


}