package com.task.nytimeaplpication.presentation.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.task.nytimeaplpication.core.BaseFragment
import com.task.nytimeaplpication.databinding.FragmentArticlesDetailsBinding
import com.task.nytimeaplpication.domain.data.model.news.NewsItem

/**
 * Created by Amer Elsayed.
 *12/26/2021
 *dev.amer.elsayed@gmail.com
 */
class ArticleDetailsFragment : BaseFragment<FragmentArticlesDetailsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArticlesDetailsBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<NewsItem>("newDetails")?.apply {
            binding.ivArticleImg.load(image)
            binding.tvArticleTitle.text = title
            binding.tvArticleBy.text = byline
            binding.tvArticleDescription.text = abstract

        }

    }

}