package com.example.movieapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.databinding.FragmentPeopleBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.adapter.MovieAdapter
import com.example.movieapp.presentation.adapter.PeopleAdapter

class PeopleFragment(val factory: MovieViewModelFactory) : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var fragmentBinding: FragmentPeopleBinding
    private lateinit var adapter: PeopleAdapter
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
        adapter = PeopleAdapter(cxt)
        fragmentBinding.recyclerView.adapter = adapter

        displayPopularPopular()
        return fragmentBinding.root
    }

    private fun displayPopularPopular() {
        fragmentBinding.progress.visibility = View.VISIBLE
        val responseLiveData = viewModel.getPeople()
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