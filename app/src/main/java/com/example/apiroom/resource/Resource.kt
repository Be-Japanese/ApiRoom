package com.example.apiroom.resource

sealed class Resource<out T> {

    object Loading:Resource<Nothing>()
    object Inserted:Resource<Nothing>()

    class Successful<R>(var data:R):Resource<R>()
    class Error(var error:Exception):Resource<Nothing>()


}