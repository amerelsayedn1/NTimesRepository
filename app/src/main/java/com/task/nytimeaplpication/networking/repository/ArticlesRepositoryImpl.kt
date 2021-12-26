package com.task.nytimeaplpication.networking.repository

import com.task.nytimeaplpication.domain.data.model.BaseModel
import com.task.nytimeaplpication.domain.data.model.news.NewsItem
import com.task.nytimeaplpication.networking.DataState
import com.task.nytimeaplpication.networking.helpers.Helper
import com.task.nytimeaplpication.networking.remote.EndPointService
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val endPointService: EndPointService
) : ArticlesRepository {

    override suspend fun getMostViewArticles(
        section: String,
        period: String
    ): DataState<BaseModel<ArrayList<NewsItem>>> {
        return Helper.getResult { endPointService.getMostViewArticles(section, period) }
    }

}