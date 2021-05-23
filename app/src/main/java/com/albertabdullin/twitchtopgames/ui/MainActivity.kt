package com.albertabdullin.twitchtopgames.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.albertabdullin.twitchtopgames.R
import com.albertabdullin.twitchtopgames.databinding.ActivityMainSwipeRefreshBinding
import com.albertabdullin.twitchtopgames.util.GamesAdapter
import com.albertabdullin.twitchtopgames.viewmodel.TwitchTopGamesViewModel
import com.albertabdullin.twitchtopgames.viewmodel.TwitchTopGamesViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainSwipeRefreshBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = TwitchTopGamesViewModelFactory(this.application)
        val viewModel = ViewModelProvider(this, factory).get(TwitchTopGamesViewModel::class.java)
        val adapter = GamesAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.getGamesList().observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.networkStateError.observe(this) {
            it?.let {
                if (it) Toast.makeText(this,getString(R.string.network_error),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}