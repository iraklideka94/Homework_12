package com.example.Homework_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework_012.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private var binding: FragmentMovieBinding? = null

    private val argument: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() = binding!!.apply {
        val movie = MovieObj.moviesList[argument.selectedGamePosition]
        Glide.with(this.root).load(movie.image).into(movieImage)
        movieTitle.text = movie.title
        description.text = movie.description
    }


}