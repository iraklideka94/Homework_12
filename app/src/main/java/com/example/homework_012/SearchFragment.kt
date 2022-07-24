package com.example.Homework_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.example.homework_012.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        searchMovie()

    }

    private fun init() {
        binding!!.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
        movieAdapter.submitList(MovieObj.moviesList.toList())
    }

    private fun searchMovie() {
        binding!!.searchET.addTextChangedListener {
            movieAdapter.submitList(searchTitle(it.toString()))
        }
        movieAdapter.onItemClickListener = { movie ->
            SearchFragmentDirections
                .actionSearchFragmentToGameFragment(MovieObj.moviesList.indexOf(movie)).also {
                    Navigation.findNavController(binding!!.root).navigate(it)
                }
        }
    }

    private fun searchTitle(title: String): List<Movie> {
        return MovieObj.moviesList.filter {
            it.title.lowercase().contains(title.lowercase())
        }
    }


}