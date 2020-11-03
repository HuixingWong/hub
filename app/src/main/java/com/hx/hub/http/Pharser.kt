package com.hx.hub.http

import com.hx.hub.entity.TrendingRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun main() {

    test2()
    Thread.sleep(10000)
}

fun test1() {
    GlobalScope.launch(Dispatchers.IO) {
        val doc: Document = Jsoup.connect("https://github.com/trending").get()
//        println(doc.body())
        val elements: Elements = doc.getElementsByTag("article")
        println("----------------------------Fount ${elements.size} repositories--------------------------------")
        elements.forEach {
            // Repo
            println("Repo: ${it.getElementsByTag("a")[1].attr("href").substring(1)}")
            // Description
            println("Description: ${it.getElementsByTag("p")[0].text()}")
            // Language
            val lang: Elements = it.getElementsByClass("repo-language-color")
            if (lang.isNotEmpty()) {
                println("Language: ${lang[0].attr("style").substringAfter(" ") + "-" + lang[0].siblingElements()[0].text()}")
            }
            // Start & Fork
            val startFork: Elements = it.getElementsByClass("muted-link d-inline-block mr-3")
            println("Start: ${startFork[0].text()}")
            println("Fork: ${startFork[1].text()}")
            // Trending
            println(it.getElementsByClass("d-inline-block float-sm-right")[0].text())
            println("------------------------------------------------------------")
//            val avatar = it.getElementsByClass("avatar mb-1 avatar-user")
//            println("avatar: $avatar")
        }
    }
}

fun test2() {
    val doc: Document = Jsoup.connect("https://github.com/trending").get()
//        println(doc.body())
    mutableListOf<TrendingRepo>()
    val elements: Elements = doc.getElementsByTag("article")
    println("----------------------------Fount ${elements.size} repositories--------------------------------")
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

        val builtBy = it.getElementsByClass("Built by")

        println()
        println(repo)
        println(desc)
        println(lang)
        println(star)
        println(fork)
        println(trending)
        println(builtBy)
    }

}

