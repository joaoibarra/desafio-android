package com.joaoibarra.gitapp.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href") val href: String
)