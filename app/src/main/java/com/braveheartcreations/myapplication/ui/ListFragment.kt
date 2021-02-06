package com.braveheartcreations.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.braveheartcreations.myapplication.MainActivity
import com.braveheartcreations.myapplication.`interface`.CallBack
import com.braveheartcreations.myapplication.adapters.ImageAdapter
import com.braveheartcreations.myapplication.databinding.FragmentListBinding
import com.braveheartcreations.myapplication.models.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** To list the images in grid view */
class ListFragment : Fragment() {

    var binding: FragmentListBinding? = null
    private var adapter = ImageAdapter(object : CallBack {
        override fun onClick(param: Any) {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(
                    param as ListItem
                )
            )
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentListBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity() as MainActivity).getViewModel()
        if (adapter.data.size == 0) {
            binding?.progress?.isVisible = true
            binding?.recyclerView?.layoutManager =
                GridLayoutManager(requireActivity(), 3, RecyclerView.VERTICAL, false)
            CoroutineScope(Dispatchers.Main).launch {
                viewModel?.getData()?.observe(requireActivity(), {
                    binding?.progress?.isVisible = false
                    binding?.recyclerView?.adapter = adapter
                    adapter.updateList(it)
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}