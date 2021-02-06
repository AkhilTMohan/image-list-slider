package com.braveheartcreations.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.braveheartcreations.myapplication.databinding.FragmentObjectBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** Full size Image view and meta data holder fragment / pager fragment  */

class ObjectFragment : Fragment() {

    var binding: FragmentObjectBinding? = null
    private val args: ObjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentObjectBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            binding?.copyright?.text = String.format("%s", "©" + args.image?.copyright)
            binding?.date?.text = args.image?.date
            binding?.title?.text = args.image?.title
            binding?.description?.text = args.image?.explanation
            CoroutineScope(Dispatchers.Main).launch {
                binding?.image?.let {
                    Glide.with(requireActivity())
                        .load(args.image?.url)
                        .into(it)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}