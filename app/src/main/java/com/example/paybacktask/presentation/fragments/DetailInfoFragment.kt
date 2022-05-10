package com.example.paybacktask.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.paybacktask.R
import com.example.paybacktask.databinding.FragmentDetailInfoBinding
import com.example.paybacktask.domain.Hit
import com.squareup.picasso.Picasso

class DetailInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailInfoBinding
    private val args by navArgs<DetailInfoFragmentArgs>()
    private var hit: Hit? = null

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
        binding.apply {
            Picasso.with(activity)
                .load(hit?.largeImageURL)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivImageBig)
            tvUserName.text = getString(R.string.user_name, hit?.user)
            tvTags.text = getString(R.string.tags, hit?.tags)
            tvLikesNumber.text = getString(R.string.likes_number, hit?.likes.toString())
            tvDownloadsNumber.text = getString(R.string.downloads_number, hit?.downloads.toString())
            tvCommentsNumber.text = getString(R.string.comments_number, hit?.comments.toString())
        }
    }
}