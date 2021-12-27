package com.task.nytimeaplpication.presentation.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.nytimeaplpication.core.NetworkHandler
import com.task.nytimeaplpication.domain.data.model.BaseModel
import com.task.nytimeaplpication.domain.data.model.news.NewsItem
import com.task.nytimeaplpication.networking.DataState
import com.task.nytimeaplpication.networking.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Amer Elsayed.
 *12/3/2021
 *dev.amer.elsayed@gmail.com
 */

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: ArticlesRepository,
) : ViewModel() {

    private val _articles = MutableLiveData<DataState<BaseModel<ArrayList<NewsItem>>>>()
    val articles: LiveData<DataState<BaseModel<ArrayList<NewsItem>>>>
        get() = _articles


    fun getMostViewArticles(
        section: String,
        period: String
    ) {
        _articles.value = DataState.Loading

        when (networkHandler.isNetworkAvailable()) {
            true -> viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getMostViewArticles(section, period)

                withContext(Dispatchers.Main) {
                    _articles.postValue(response)
                }
            }
            false -> _articles.value = DataState.Error(message = "Check your Network Connection")
        }

    }
}