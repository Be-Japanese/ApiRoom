package com.example.apiroom.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apiroom.data.model.PostDbModel


@Dao
interface RoomService {

    @Query("select * from posts")
     suspend fun getposts():List<PostDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( post:PostDbModel): Long
}