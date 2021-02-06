package com.braveheartcreations.myapplication.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.braveheartcreations.myapplication.models.ListItem
import com.braveheartcreations.myapplication.ui.ObjectFragment

/** Pager adapter to swipe between images */

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val itemImage: String = "image"

    var data = ArrayList<ListItem>()

    override fun getItemCount(): Int = data.size

    fun updateList(item: ArrayList<ListItem>) {
        if (!data.containsAll(item)) {
            data.addAll(item)
            notifyDataSetChanged()
        }
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(itemImage, data[position])
        }
        return fragment
    }
}
