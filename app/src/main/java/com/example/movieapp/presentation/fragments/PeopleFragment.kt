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
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentPeopleBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.adapter.PeopleAdapter


class PeopleFragment(val factory: MovieViewModelFactory) : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var fragmentBinding: FragmentPeopleBinding
    private lateinit var adapter: PeopleAdapter
    private lateinit var cxt: Context
    private var page: Int = 1
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
        initScrollListener()

        return fragmentBinding.root
    }


    private fun displayPopularPopular() {
        fragmentBinding.progress.visibility = View.VISIBLE

        val responseLiveData = viewModel.getPeople(page)
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

    var isLoading = false
    private fun initScrollListener() {
        fragmentBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.peopleList.size - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        // adapter.peopleList.add(null)
        adapter.notifyItemInserted(adapter.peopleList.size - 1)
        adapter.peopleList.removeAt(adapter.peopleList.size - 1)
        val scrollPosition: Int = adapter.peopleList.size
        adapter.notifyItemRemoved(scrollPosition)
        var currentSize = scrollPosition
        page = currentSize++
        displayPopularPopular()
        adapter.notifyDataSetChanged()
        isLoading = false
    }


}