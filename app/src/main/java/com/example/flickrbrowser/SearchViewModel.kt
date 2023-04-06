package com.example.flickrbrowser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _photoList = MutableLiveData<List<Photo>>()
    val photoList:LiveData<List<Photo>>
        get() = _photoList
    init{
        viewModelScope.launch {
            _photoList.value = FlickrApi.retrofitService.getPhotos(tags = "dog").photos.photo
            Log.d("photos", _photoList.value.toString())
        }
    }
    fun setTags(tag:String){
        viewModelScope.launch {
            _photoList.value = FlickrApi.retrofitService.getPhotos(tags = tag).photos.photo
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.v("viewModel","data cleared")
    }

}