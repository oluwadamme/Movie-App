package com.example.movieapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.adapter.MovieAdapter


class MovieFragment(val factory: MovieViewModelFactory) : Fragment() {


    private lateinit var viewModel: MovieViewModel
    private lateinit var fragmentBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var cxt: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = FragmentMovieBinding.inflate(inflater, container, false)
       viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        cxt = fragmentBinding.root.context
        fragmentBinding.recyclerView.layoutManager = LinearLayoutManager(cxt)
        adapter = MovieAdapter(cxt)
        fragmentBinding.recyclerView.adapter = adapter

        displayPopularMovies()
        return fragmentBinding.root
    }

    private fun displayPopularMovies() {
        fragmentBinding.progress.visibility = View.VISIBLE
        val responseLiveData = viewModel.getMovies()
        responseLiveData.observe(
            viewLifecycleOwner, Observer {
                if (it != null) {
                    Log.i("TAGY", "${it.size}")
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    fragmentBinding.progress.visibility = View.GONE
                } else {
                    fragmentBinding.progress.visibility = View.GONE
                    Toast.makeText(cxt, "No Data", Toast.LENGTH_LONG).show()
                }
            }
        )
    }


}