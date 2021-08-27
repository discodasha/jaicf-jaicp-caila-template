package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {

    state("smalltalk", noContext = true) {
        globalActivators {
            intent("Birthday")
        }

        action(caila) {
            activator.topIntent.answer?.let { reactions.say(it) } ?: reactions.go("/fallback")
        }
    }

    fallback {
        reactions.say("Вот и встретились два одиночества...")
    }
}