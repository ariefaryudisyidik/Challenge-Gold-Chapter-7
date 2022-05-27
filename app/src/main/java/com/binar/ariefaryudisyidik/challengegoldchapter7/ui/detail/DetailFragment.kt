package com.binar.ariefaryudisyidik.challengegoldchapter7.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.binar.ariefaryudisyidik.challengegoldchapter7.databinding.FragmentDetailBinding
import com.binar.ariefaryudisyidik.challengegoldchapter7.ui.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDetailMovie()
    }

    private fun showDetailMovie() {
        binding.apply {
            Glide.with(requireView())
                .load(HomeAdapter.posterBaseUrl + args.movie.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivBackdrop)
            Glide.with(requireView())
                .load(HomeAdapter.posterBaseUrl + args.movie.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPoster)
            tvTitle.text = args.movie.title
            tvOverview.text = args.movie.overview
            tvReleaseDate.text = args.movie.releaseDate
            tvAverageRating.text = args.movie.voteAverage.toString()
            tvRateCount.text = args.movie.voteCount.toString()
            tvPopularity.text = args.movie.popularity.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}