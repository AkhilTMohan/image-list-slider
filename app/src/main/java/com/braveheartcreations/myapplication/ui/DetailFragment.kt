package com.braveheartcreations.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.braveheartcreations.myapplication.MainActivity
import com.braveheartcreations.myapplication.adapters.PagerAdapter
import com.braveheartcreations.myapplication.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** Image detail fragment which holds the viewpager */

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var demoCollectionAdapter: PagerAdapter
    var binding: FragmentDetailBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentDetailBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity() as MainActivity).getViewModel()
        demoCollectionAdapter = PagerAdapter(this)
        binding?.viewpager?.adapter = demoCollectionAdapter
        TabLayoutMediator(binding?.tabLayout!!, binding?.viewpager!!) { tab, position ->
            tab.text = "Image ${(position + 1)}"
        }.attach()
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.getData()?.observe(requireActivity(), {
                demoCollectionAdapter.updateList(it)
                val index = demoCollectionAdapter.data.indexOf(args.image)
                binding?.tabLayout?.getTabAt(index)?.select()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}