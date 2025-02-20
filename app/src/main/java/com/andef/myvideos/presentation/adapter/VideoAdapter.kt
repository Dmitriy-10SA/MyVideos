package com.andef.myvideos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.andef.myvideos.R
import com.andef.myvideos.databinding.VideoItemBinding
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.presentation.parser.DurationParser
import com.bumptech.glide.Glide
import javax.inject.Inject

class VideoAdapter @Inject constructor() : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        Glide.with(holder.itemView).load(video.thumbnailUrl).into(holder.imageViewVideoThumbnail)
        holder.textViewVideoTitle.text = video.title
        holder.textViewVideoDuration.text = DurationParser.parse(video.duration)
    }

    class VideoViewHolder(binding: VideoItemBinding) : ViewHolder(binding.root) {
        val cardViewVideo = binding.cardViewVideo
        val imageViewVideoThumbnail = binding.imageViewVideoThumbnail
        val textViewVideoTitle = binding.textViewVideoTitle
        val textViewVideoDuration = binding.textViewVideoDuration
    }
}