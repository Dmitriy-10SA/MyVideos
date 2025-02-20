package com.andef.myvideos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andef.myvideos.data.mapper.VideoDTOToVideoMapper
import com.andef.myvideos.data.mapper.VideoItemListHolderDTOToVideoIdListMapper
import com.andef.myvideos.data.network.api.ApiFactory
import com.andef.myvideos.data.repository.VideoRepositoryImpl
import com.andef.myvideos.databinding.ActivityMainBinding
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.presentation.adapter.VideoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapterAndRecyclerView()
    }

    private fun initAdapterAndRecyclerView() {
        videoAdapter = VideoAdapter()

        binding.recyclerViewVideos.adapter = videoAdapter
    }
}