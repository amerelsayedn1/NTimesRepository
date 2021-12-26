package com.task.nytimeaplpication.presentation.articles

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.task.nytimeaplpication.R
import com.task.nytimeaplpication.core.BaseFragment
import com.task.nytimeaplpication.databinding.FragmentArticlesBinding
import com.task.nytimeaplpication.networking.DataState
import com.task.nytimeaplpication.utils.SpacesItemDecorator
import com.task.nytimeaplpication.utils.extension.gone
import com.task.nytimeaplpication.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Amer Elsayed.
 *12/25/2021
 *dev.amer.elsayed@gmail.com
 */

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    private lateinit var articlesAdapter: ArticlesAdapter
    private val viewModel: ArticleViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArticlesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initArticlesRv()
        viewModel.getMostViewArticles("all-sections", "7.json")

        viewModel.articles.observe(viewLifecycleOwner, {

            when (it) {

                is DataState.Loading -> {
                    binding.pBLoader.visible()
                }

                is DataState.Error -> {
                    binding.pBLoader.gone()
                    toast(it.message)
                }

                is DataState.Success -> {
                    binding.pBLoader.gone()
                    articlesAdapter.submitList(it.data.data)
                }

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu, menu)
    }

    private fun initArticlesRv() = with(binding) {
        // init article rv
        articlesAdapter = ArticlesAdapter().also {
            articleRv.adapter = it
            articleRv.addItemDecoration(SpacesItemDecorator(16))
        }

        articlesAdapter.setOnClickListener { _, item, _ ->
            findNavController().navigate(
                R.id.action_articlesFragment_to_articleDetailsFragment,
                bundleOf("newDetails" to item)
            )
        }
    }

}