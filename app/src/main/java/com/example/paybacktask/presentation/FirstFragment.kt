package com.example.paybacktask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val pixabyViewModel: PixabyViewModel by activityViewModels {
        PixabyViewModel.PixabyViewModelFactory(
            (activity?.application as PixabyApplication).getAllUseCase,
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
        binding.apply{
            btn1.setOnClickListener {
                pixabyViewModel.getAllPictures()
//            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment("Жажжа"))
            }
        }
        pixabyViewModel.pixabayResponse.observe(viewLifecycleOwner, {
            binding.apply {
                tv1.text = it.hits[0].tags
                tv2.text = it.hits[1].tags
                tv3.text = it.hits[2].tags
            }
        })
    }
}