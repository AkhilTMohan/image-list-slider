package com.braveheartcreations.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.braveheartcreations.myapplication.models.ListItem
import kotlinx.coroutines.Dispatchers.IO

/** View model class which uses livedata scope to get the data from raw file*/

class MainViewModel(private val context: Application) : AndroidViewModel(context) {
    private var items = arrayListOf<ListItem>()
    fun getData() = liveData(IO) {
        if (items.size != 0) {
            emit(items)
        } else{
            items = DataRepository.getImagesFromFile(context)
            emit(items)
        }
    }
}