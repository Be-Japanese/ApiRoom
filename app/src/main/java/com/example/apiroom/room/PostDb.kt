package com.example.apiroom.room

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apiroom.data.model.PostDbModel


@Database(entities = [PostDbModel::class], version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = PostDb.migrate2to3::class),

                     ], exportSchema = true)
abstract class PostDb:RoomDatabase() {
    abstract fun postsDao():RoomService
    companion object {

        val migrate3to4 = object:Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table if not exist Story(id int not null primary key)")
            }

        }


        var instance: RoomDatabase? = null
        fun initDB(context: Context): RoomDatabase {

            if (instance == null) {

                instance = Room.databaseBuilder(
                    context,
                    PostDb::class.java,
                    "postdb"
                ).addMigrations(PostDb.migrate3to4)
                    .build()
                return instance!!
            }
            return instance!!
        }
    }

    @RenameColumn(tableName = "posts", fromColumnName = "created", toColumnName = "createdAt")
    class migrate2to3:AutoMigrationSpec



}

