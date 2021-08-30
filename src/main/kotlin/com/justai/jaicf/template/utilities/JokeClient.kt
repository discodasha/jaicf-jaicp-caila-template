package com.justai.jaicf.template.utilities

import com.justai.jaicf.helpers.logging.WithLogger
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

val JSON: Json = Json {
    encodeDefaults = false
    isLenient = true
    ignoreUnknownKeys = true
}

// дз - спрашивать, показывать ли эксплисит шутки, если нет, то генерировать еще,
// но не более 5 раз.

class JokeClient(): WithLogger {

    private val url = "https://v2.jokeapi.dev/joke/Any"

    private val client = HttpClient {
        expectSuccess = true
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(JSON)
        }
    }

    private fun getRawJoke(): JokeResponse? {
        try {
            return runBlocking {
                client.get(url) {}
            }
        }
        catch (ex: Exception) {
            logger.warn("Failed to get definition", ex)
        }
        return null
    }

    fun getJoke(): Joke? {
        return getRawJoke()?.let { Joke(it) }
    }

}

