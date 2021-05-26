package com.albertabdullin.twitchtopgames.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.albertabdullin.twitchtopgames.R
import com.albertabdullin.twitchtopgames.databinding.ActivityMainSwipeRefreshBinding
import com.albertabdullin.twitchtopgames.util.GamesAdapter
import com.albertabdullin.twitchtopgames.viewmodel.TwitchTopGamesViewModel
import com.albertabdullin.twitchtopgames.viewmodel.TwitchTopGamesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainSwipeRefreshBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = TwitchTopGamesViewModelFactory(this.application)
        val viewModel = ViewModelProvider(this, factory).get(TwitchTopGamesViewModel::class.java)
        val adapter = GamesAdapter()
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                if (binding.root.isRefreshing) binding.root.isRefreshing = false
                adapter.submitData(it)
            }
        }
        binding.root.setOnRefreshListener {  adapter.refresh() }
    }
}