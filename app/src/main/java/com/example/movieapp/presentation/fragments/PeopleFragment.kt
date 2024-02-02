package com.example.movieapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.databinding.FragmentPeopleBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.adapter.MovieAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PeopleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PeopleFragment(val factory: MovieViewModelFactory) : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var fragmentBinding: FragmentPeopleBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var cxt: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentPeopleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        cxt = fragmentBinding.root.context
        fragmentBinding.recyclerView.layoutManager = LinearLayoutManager(cxt)
        adapter = MovieAdapter(cxt)
        fragmentBinding.recyclerView.adapter = adapter

        displayPopularPopular()
        return fragmentBinding.root
    }

    private fun displayPopularPopular() {

    }


}