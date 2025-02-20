package com.andef.myvideos.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.andef.myvideos.databinding.ActivityMainBinding
import com.andef.myvideos.presentation.adapter.VideoAdapter
import com.andef.myvideos.presentation.application.MyVideosApplication
import com.andef.myvideos.presentation.factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as MyVideosApplication).component
    }

    @Inject
    lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapterAndRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.videos.observe(this) { videos ->
            videoAdapter.submitList(videos)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.swipeRefreshLayoutVideos.isRefreshing = isLoading
        }
        viewModel.getVideos()
    }

    private fun initAdapterAndRecyclerView() {
        videoAdapter = VideoAdapter()

        binding.recyclerViewVideos.adapter = videoAdapter
    }
}