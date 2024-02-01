package com.example.movieapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.presentation.fragments.MovieFragment
import com.example.movieapp.presentation.fragments.PeopleFragment
import com.example.movieapp.presentation.fragments.TVFragment
import javax.inject.Inject

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle,val factory: MovieViewModelFactory ):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return PeopleFragment()
            1 -> return MovieFragment(factory)
        }
        return TVFragment()
    }
}