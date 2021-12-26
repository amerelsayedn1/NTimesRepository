package com.task.nytimeaplpication.networking.remote

import com.task.nytimeaplpication.domain.data.model.BaseModel
import com.task.nytimeaplpication.domain.data.model.news.NewsItem
import com.task.nytimeaplpication.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointService {

    @GET("mostpopular/v2/mostviewed/{section}/{period}")
    suspend fun getMostViewArticles(
        @Path("section") section: String,
        @Path("period") period: String,
        @Query("api-key") appId: String = Constants.appId
    ): Response<BaseModel<ArrayList<NewsItem>>>

}