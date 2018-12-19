package com.joaoibarra.gitapp.model.api

import com.joaoibarra.gitapp.model.Pull
import com.joaoibarra.gitapp.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi {
    @Headers(
            "Accept: application/vnd.github.v3.text-match+json",
            "Content-type:application/json"
    )
    @GET("search/repositories")
    fun searchByQuery(
        @Query("q") q: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): Observable<Result>

    @Headers(
            "Accept: application/vnd.github.v3.text-match+json",
            "Content-type:application/json"
    )
    @GET("repos/{owner}/{repo}/pulls")
    fun searchPullByRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int
    ): Observable<List<Pull>>
}