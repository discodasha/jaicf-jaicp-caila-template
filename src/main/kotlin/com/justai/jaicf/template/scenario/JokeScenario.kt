package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.template.utilities.JokeClient

val jokeScenario = Scenario {

    val jokesClient = JokeClient()

    state("joke_scenario_entry") {
        activators {
            regex("/joke")
        }

        action {
            val joke = jokesClient.getJoke()
            if (joke != null) {
                reactions.say(joke.build())
            } else {
                reactions.say("No joke for today, mate.")
            }
        }
    }

}