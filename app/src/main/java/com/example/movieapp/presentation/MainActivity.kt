package com.example.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Insert
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.presentation.di.Injector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as Injector).createMovieSubComponent().inject(this)

        viewModel=ViewModelProvider(this,factory).get(MovieViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter=MovieAdapter()
        binding.recyclerView.adapter=adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.progress.visibility= View.VISIBLE
        val responseLiveData=viewModel.getMovies()
        responseLiveData.observe(
            this, Observer {
                if (it!=null){
                    Log.i("TAGY","${it.size}")
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.progress.visibility= View.GONE
                }else{
                    binding.progress.visibility= View.GONE
                    Toast.makeText(applicationContext,"No Data",Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.update,menu)

        return true
    }

    private fun updateMovies() {
        binding.progress.visibility= View.VISIBLE
        val responseLiveData=viewModel.updateMovies()
        responseLiveData.observe(
            this, Observer {
                if (it != null){
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.progress.visibility= View.GONE
                }else{
                    binding.progress.visibility= View.GONE
                    Toast.makeText(applicationContext,"No Data",Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.update_menu->{
                updateMovies()
                true
            }else-> super.onOptionsItemSelected(item)

        }
    }
}