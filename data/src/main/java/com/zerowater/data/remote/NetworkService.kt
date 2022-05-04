package com.zerowater.data.remote

import com.zerowater.data.model.GithubRepoRes
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("users/{owner}/repos")
    suspend fun getRepos(
        @Path("owner") owner: String
    ): List<GithubRepoRes>
}