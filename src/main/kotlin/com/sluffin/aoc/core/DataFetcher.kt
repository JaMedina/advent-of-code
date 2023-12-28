package com.sluffin.aoc.core

import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.cache.storage.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.nio.file.Files
import java.nio.file.Paths

object DataFetcher {
    private val httpClient = HttpClient(CIO) {
        install(HttpCookies) {
            storage = ConstantCookiesStorage(
                Cookie(
                    name = "session",
                    value = "53616c7465645f5ff6a64c616700a51d5152bbc1059f1c368f755b585f125c4b86da0c6028f2e6fc81a1cdc024878268315df2b73ff107231d741c14a286595b",
                    domain = ".adventofcode.com",
                    encoding = CookieEncoding.RAW,
                )
            )
        }
        install(HttpCache) {
            val cacheFile = Files.createDirectories(Paths.get("build/cache")).toFile()
            publicStorage(FileStorage(cacheFile))
            privateStorage(FileStorage(cacheFile))
        }
    }

    suspend fun fetch(year: Int, day: Int): String =
        httpClient.get("https://adventofcode.com/$year/day/$day/input").body()
}