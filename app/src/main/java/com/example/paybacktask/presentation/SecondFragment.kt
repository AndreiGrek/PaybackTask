package com.example.paybacktask.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.paybacktask.R
import com.example.paybacktask.databinding.FragmentFirstBinding
import com.example.paybacktask.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val args by navArgs<SecondFragmentArgs>()
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn2.apply{
            text = args.name
            setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
            }
        }
    }

}