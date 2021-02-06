package com.braveheartcreations.myapplication

import android.content.Context
import com.braveheartcreations.myapplication.models.ListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/** Data repository to fetch the image from raw folder */

object DataRepository {
    fun getImagesFromFile(context: Context): ArrayList<ListItem> {
        val text = context.resources.openRawResource(R.raw.data)
            .bufferedReader().use { it.readText() }
        val imageList = Gson().fromJson<ArrayList<ListItem>>(
            text,
            object : TypeToken<ArrayList<ListItem>>() {}.type
        )
        imageList.sortByDescending { getDateObject(it.date) }
        return imageList
    }

    private fun getDateObject(date: String): Date? {
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.parse(date)
    }
}