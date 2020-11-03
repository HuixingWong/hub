package com.hx.hub.ui.main.trending.dataSource

import com.hx.architecture.core.base.repository.IRemoteDataSource
import com.hx.hub.base.Results
import com.hx.hub.entity.TrendingRepo
import com.hx.hub.entity.request.TrendingRequestData
import com.hx.hub.http.service.ServiceManager
import com.hx.hub.utils.processApiResponse
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val serviceManager: ServiceManager)
    : IRemoteDataSource{

    suspend fun queryTrendings(trendingRequestData: TrendingRequestData): Results<List<TrendingRepo>> {
        return processApiResponse {
            serviceManager.trendingService.fetchTrendingRepos(trendingRequestData.language,
                    trendingRequestData.since, trendingRequestData.spokenLanguage) }
    }

    suspend fun parsingTrending(): Results<List<TrendingRepo>> {
        val doc: Document = Jsoup.connect("https://github.com/trending").get()
        mutableListOf<TrendingRepo>()
        val elements: Elements = doc.getElementsByTag("article")
        println("----------------------------Fount ${elements.size} repositories--------------------------------")
        val listOf = mutableListOf<TrendingRepo>()
        elements.forEach {
            // Repo
            val repo = it.getElementsByTag("a")[1].attr("href").substring(1)
            val desc = it.getElementsByTag("p")[0].text()
            var lang = it.getElementsByClass("repo-language-color").getOrNull(0)?.let { la ->
                "${la.attr("style").substringAfter(" ")} ${la.siblingElements()[0].text()}"
            }
            var star = it.getElementsByClass("muted-link d-inline-block mr-3")?.getOrNull(0)?.text()
            var fork = it.getElementsByClass("muted-link d-inline-block mr-3")?.getOrNull(1)?.text()
            val trending = it.getElementsByClass("d-inline-block float-sm-right")[0].text()
        }
        return if (listOf.isNullOrEmpty()) Results.failure(Throwable("null")) else Results.success(listOf)
    }

}