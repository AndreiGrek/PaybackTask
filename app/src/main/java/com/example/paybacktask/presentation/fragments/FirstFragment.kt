package com.example.paybacktask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.R
import com.example.paybacktask.databinding.FragmentFirstBinding
import com.example.paybacktask.presentation.PicturesAdapter
import com.example.paybacktask.presentation.viewmodels.PixabyViewModel
import com.example.paybacktask.domain.Utils

class FirstFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as PixabyApplication).component
    }

    private val FRUITS = "fruits"
    private lateinit var recyclerView: RecyclerView
    private lateinit var picturesAdapter: PicturesAdapter
    private lateinit var binding: FragmentFirstBinding
    private val pixabyViewModel: PixabyViewModel by activityViewModels {
        PixabyViewModel.PixabyViewModelFactory(
            (activity?.application as PixabyApplication).getAllHitsUseCase,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        initOnclickListeners()
        observeViewModel()
    }

    private fun sendRequest(query: String = FRUITS) {
        pixabyViewModel.getAllPictures(query)
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_hits_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initOnclickListeners() {
        binding.btnSearch.setOnClickListener {
            if (Utils.hasNetwork(requireContext())) {
                val query = binding.etSearch.text.toString()
                if (query.isEmpty()) {
                    sendRequest(FRUITS)
                } else {
                    sendRequest(query)
                }
            } else {
                Toast.makeText(activity, R.string.no_internet_connection, LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        pixabyViewModel.pixabayResponse.observe(viewLifecycleOwner) {
            picturesAdapter = PicturesAdapter(it.hits)
            recyclerView.adapter = picturesAdapter
            picturesAdapter.notifyDataSetChanged()
        }
    }
}