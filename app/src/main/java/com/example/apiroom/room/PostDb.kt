package com.example.apiroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apiroom.data.model.PostDbModel


@Database(entities = [PostDbModel::class], version = 1)
abstract class PostDb:RoomDatabase() {

    abstract fun storiosDao():RoomService


    companion object {

        var instinc: RoomDatabase? = null
        fun initDB(context: Context): RoomDatabase {
            if (instinc == null) {

                instinc = Room.databaseBuilder(
                    context,
                    PostDb::class.java,
                    "postdb"
                ).build()
                return instinc!!
            }
            return instinc!!
        }
    }
}

