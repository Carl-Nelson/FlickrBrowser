package com.example.flickrbrowser


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickrbrowser.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var vm: SearchViewModel
    private lateinit var binding:FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        vm = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.searchButton.isEnabled = false
        binding.viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rv
        rv.layoutManager = GridLayoutManager(rv.context,2)

        val adapter = PropertyListAdapter()
        vm.photoList.observe(viewLifecycleOwner){
            if(it!=null){
                adapter.setData(it)
            }
        }
        rv.adapter = adapter

        binding.searchBox.setOnFocusChangeListener { view, b ->
            binding.searchButton.isEnabled = true
        }
        binding.searchButton.setOnClickListener{
            val searchInput = binding.searchBox.text.toString()
            vm.setTags(searchInput)

            val mgr: InputMethodManager =
                activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

            val view = this.view?.rootView?.windowToken
            mgr.hideSoftInputFromWindow(view, 0)

        }

        binding.lifecycleOwner = viewLifecycleOwner

    }
}