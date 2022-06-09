package com.example.paybacktask.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.R
import com.example.paybacktask.databinding.FragmentDetailInfoBinding
import com.example.paybacktask.domain.Hit
import com.example.paybacktask.presentation.ViewModelFactory
import com.example.paybacktask.presentation.viewmodels.DetailedInfoViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailInfoFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as PixabyApplication).component
    }

    private lateinit var binding: FragmentDetailInfoBinding
    private val args by navArgs<DetailInfoFragmentArgs>()
    private var hit: Hit? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val detailedInfoViewModel: DetailedInfoViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hit = args.hit
        hit?.let { detailedInfoViewModel.setHitData(it) }
        observeViewModel()
    }

    private fun observeViewModel() {
        detailedInfoViewModel.hitData.observe(viewLifecycleOwner) {
            binding.apply {
                Picasso.with(activity)
                    .load(it.largeImageURL)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivImageBig)
                tvUserName.text = getString(R.string.user_name, it.user)
                tvTags.text = getString(R.string.tags, it.tags)
                tvLikesNumber.text = getString(R.string.likes_number, it.likes.toString())
                tvDownloadsNumber.text =
                    getString(R.string.downloads_number, it.downloads.toString())
                tvCommentsNumber.text = getString(R.string.comments_number, it.comments.toString())
            }
        }
    }
}