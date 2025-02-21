package com.andef.myvideos.data.network.api

import com.andef.myvideos.data.network.dto.video.VideoDTO
import com.andef.myvideos.data.network.dto.videolist.VideoItemListHolderDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getVideoItemList(
        @Query(QUERY_PARAM_PAGE_TOKEN) pageToken: String = "",
        @Query(QUERY_PARAM_TYPE) type: String = "video",
        @Query(QUERY_PARAM_QUERY) query: String = "",
        @Query(QUERY_PARAM_MAX_RESULT) maxResult: Int = 20,
        @Query(QUERY_PARAM_EVENT_TYPE) eventType: String = "completed",
        @Query(QUERY_PARAM_PART) part: String = "snippet",
        @Query(QUERY_PARAM_VIDEO_DURATION) duration: String = "medium",
        @Query(QUERY_PARAM_LIVE_BROADCAST_CONTENT) liveContent: String = "none",
        @Query(QUERY_PARAM_LIVE_RELEVANCE_LANGUAGE) language: String = "ru",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): VideoItemListHolderDTO

    @GET("videos")
    suspend fun getVideo(
        @Query(QUERY_PARAM_VIDEO_ID) id: String,
        @Query(QUERY_PARAM_PART) part: String = "snippet,contentDetails",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): VideoDTO

    companion object {
        private const val QUERY_PARAM_API_KEY = "key"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_QUERY = "q"
        private const val QUERY_PARAM_MAX_RESULT = "maxResults"
        private const val QUERY_PARAM_EVENT_TYPE = "eventType"
        private const val QUERY_PARAM_PART = "part"
        private const val QUERY_PARAM_VIDEO_DURATION = "videoDuration"
        private const val QUERY_PARAM_LIVE_BROADCAST_CONTENT = "liveBroadcastContent"
        private const val QUERY_PARAM_LIVE_RELEVANCE_LANGUAGE = "relevanceLanguage"
        private const val QUERY_PARAM_VIDEO_ID = "id"
        private const val QUERY_PARAM_PAGE_TOKEN = "pageToken"
        private const val API_KEY = "AIzaSyCCCtKqQSjlcn42QJwz_3etGWraI8BecsI"
    }
}