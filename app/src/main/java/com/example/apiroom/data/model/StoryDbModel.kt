package com.example.apiroom.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Story")
data class StoryDbModel(

    @PrimaryKey(autoGenerate = false)
    val id:Int
)
