package com.example.paybacktask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.R
import com.example.paybacktask.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private  val FRUITS = "fruits"
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
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pixabyViewModel.getAllPictures(FRUITS)
        recyclerView = view.findViewById(R.id.rv_hits_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            pixabyViewModel.getAllPictures(query)
        }

        pixabyViewModel.pixabayResponse.observe(viewLifecycleOwner, {
            picturesAdapter = PicturesAdapter(it.hits)
            recyclerView.adapter = picturesAdapter
            picturesAdapter.notifyDataSetChanged()
        })
    }
}