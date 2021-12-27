package com.task.nytimeaplpication.presentation.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.nytimeaplpication.core.NetworkHandler
import com.task.nytimeaplpication.domain.data.model.BaseModel
import com.task.nytimeaplpication.domain.data.model.news.Media
import com.task.nytimeaplpication.domain.data.model.news.MediaMetadata
import com.task.nytimeaplpication.domain.data.model.news.NewsItem
import com.task.nytimeaplpication.networking.DataState
import com.task.nytimeaplpication.networking.repository.ArticlesRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule

/**
 * Created by Amer Elsayed.
 * 12/26/2021
 * dev.amer.elsayed@gmail.com
 */

class ArticleViewModelTest {

    private var articleRepo: ArticlesRepository = mockk()
    private var networkHandler: NetworkHandler = mockk()

    private var articleViewModel = ArticleViewModel(networkHandler, articleRepo)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }




    @ExperimentalCoroutinesApi
    @Test
    fun `get Articles Work Successfully`() {
        runBlockingTest {
            val mockNewsItem = NewsItem(
                uri = "nyt://article/6f45b821-a697-523e-829e-66556e55bf63",
                url = "https://www.nytimes.com/2021/12/21/well/live/omicron-variant-symptoms-covid.html",
                id = 100000008129516,
                assetId = 100000008129516,
                source = "New York Times",
                publishedDate = "2021-12-21",
                updated = "2021-12-23 00:10:05",
                section = "Well",
                subsection = "Live",
                nytdsection = "well",
                byline = "By Melinda Wenner Moyer",
                type = "Article",
                title = "What Are the Symptoms of Omicron?",
                abstract = "While there are subtle differences between the latest coronavirus strain and previous ones, so far the signs of infection look pretty similar.",
                etaId = 0,
                media = listOf(
                    Media(
                        type = "image",
                        subtype = "photo",
                        caption = "New coronavirus case reports in New York are surging, and testing sites are backed up.",
                        copyright = "Gabby Jones for The New York Times",
                        mediaMetadata = arrayListOf(
                            MediaMetadata(
                                url = "https://static01.nyt.com/images/2021/12/17/nyregion/00nyvirus-coldvid02/00nyvirus-coldvid02-thumbStandard.jpg",
                                format = "Standard Thumbnail",
                                height = 75,
                                width = 75
                            ), MediaMetadata(
                                url = "https://static01.nyt.com/images/2021/12/17/nyregion/00nyvirus-coldvid02/00nyvirus-coldvid02-mediumThreeByTwo210.jpg",
                                format = "mediumThreeByTwo210",
                                height = 140,
                                width = 210
                            ),
                            MediaMetadata(
                                url = "https://static01.nyt.com/images/2021/12/17/nyregion/00nyvirus-coldvid02/00nyvirus-coldvid02-mediumThreeByTwo440.jpg",
                                format = "mediumThreeByTwo440",
                                height = 293,
                                width = 440
                            )
                        ),
                        approvedForSyndication = 1
                    )
                )
            )

            val mockObj = BaseModel(
                status = "OK",
                copyright = "Copyright (c) 2021 The New York Times Company.  All Rights Reserved.",
                num_results = 20,
                data = arrayListOf(mockNewsItem)
            )

            every{networkHandler.isNetworkAvailable()} returns true

            //for suspend functions
            coEvery { articleRepo.getMostViewArticles("all-sections", "7.json") } answers {
                DataState.Success(mockObj)
            }

            articleViewModel.getMostViewArticles("all-sections", "7.json")

            //articleRepo.getMostViewArticles("all-sections", "7.json")

            coVerify(exactly = 1) {
                articleRepo.getMostViewArticles("all-sections", "7.json")
            }

            val response = articleViewModel.articles.value as DataState.Success

            Assert.assertEquals("OK", response.data.status)
            Assert.assertEquals(
                "Copyright (c) 2021 The New York Times Company.  All Rights Reserved.",
                response.data.copyright
            )
            Assert.assertEquals(20, response.data.num_results)

            Assert.assertEquals(mockObj, response.data)
        }
    }


}