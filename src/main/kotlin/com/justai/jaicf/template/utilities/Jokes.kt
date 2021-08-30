package com.justai.jaicf.template.utilities

class Joke(raw: JokeResponse) {

    private val rawJoke: JokeResponse = raw

    fun build(): String {
        return when (rawJoke.delivery) {
            null -> rawJoke.setup
            else -> return "${rawJoke.setup}\n\n${rawJoke.delivery}"
        }

    }

    fun isOffensive(): Boolean {
        return rawJoke.flags.explicit
                || rawJoke.flags.nsfw
                || rawJoke.flags.political
                || rawJoke.flags.religious
                || rawJoke.flags.racist
                || rawJoke.flags.sexist
    }
}