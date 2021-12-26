package com.task.nytimeaplpication.networking.repository

import com.task.nytimeaplpication.domain.data.model.BaseModel
import com.task.nytimeaplpication.domain.data.model.news.NewsItem
import com.task.nytimeaplpication.networking.DataState

/**
 *Created by Amer Elsayed.
 *12/3/2021
 *dev.amer.elsayed@gmail.com
 */

interface ArticlesRepository {
    suspend fun getMostViewArticles(
        section: String,
        period: String
    ): DataState<BaseModel<ArrayList<NewsItem>>>
}