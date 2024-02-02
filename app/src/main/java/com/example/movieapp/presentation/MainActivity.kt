package com.example.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.adapter.MovieAdapter
import com.example.movieapp.presentation.adapter.ViewPagerAdapter
import com.example.movieapp.presentation.di.Injector
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel
    private val tabArray = arrayOf("People", "Movies", "TV")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as Injector).createMovieSubComponent().inject(this)
        fragmentBinding = FragmentMovieBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        initLayoutView()
    }

    private fun initLayoutView() {
        adapter=MovieAdapter(applicationContext)
        val myAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle,factory)
        binding.viewPager.adapter = myAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabArray[position]
        }.attach()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)

        return true
    }

    private fun updateMovies() {
        fragmentBinding.progress.visibility = View.VISIBLE
        val responseLiveData = viewModel.updateMovies()
        responseLiveData.observe(
            this, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    fragmentBinding.progress.visibility = View.GONE
                } else {
                    fragmentBinding.progress.visibility = View.GONE
                    Toast.makeText(applicationContext, "No Data", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_menu -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}