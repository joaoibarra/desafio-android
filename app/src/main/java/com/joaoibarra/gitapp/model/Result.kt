package com.joaoibarra.gitapp.model

data class Result(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repo>

)