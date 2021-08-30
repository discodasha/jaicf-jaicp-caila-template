package com.justai.jaicf.template.utilities

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val category: String,
    val delivery: String?=null,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)

@Serializable
data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)