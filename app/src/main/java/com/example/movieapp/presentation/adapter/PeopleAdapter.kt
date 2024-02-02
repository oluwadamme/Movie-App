package com.example.movieapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.People
import com.example.movieapp.databinding.ListItemBinding

class PeopleAdapter(val context: Context): RecyclerView.Adapter<PeopleAdapter.MyViewHolder>()  {
    val peopleList=ArrayList<People>()

    fun setList(people: List<People>){
        peopleList.clear()
        peopleList.addAll(people)
    }
    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(people: People){
            binding.descText.text="Popular Movies: "+ people.movies.joinToString()
            binding.titleText.text=people.name
            val posterUrl="https://image.tmdb.org/t/p/w500"+ people.image
            Glide.with(binding.imageView.context).load(posterUrl).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return peopleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(peopleList[position])
    }
}