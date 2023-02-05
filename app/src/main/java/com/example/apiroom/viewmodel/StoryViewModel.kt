package com.example.apiroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.repository.PostRepositoryImp
import com.example.apiroom.resource.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class postViewModel:ViewModel() {
    val postrepo = PostRepositoryImp()

    var postlivedata = MutableLiveData<Resource<List<postEntity>>>()

    fun getallposts()
    {

       postrepo.getsotries().onEach {

           postlivedata.value = it

       }.launchIn(viewModelScope)



    }

}