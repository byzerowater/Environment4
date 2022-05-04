package com.zerowater.data.source

import com.zerowater.data.model.GithubRepoRes
import com.zerowater.data.remote.NetworkService
import javax.inject.Inject

interface RemoteSource {
    suspend fun getRepos(owner: String): List<GithubRepoRes>
}

class RemoteSourceImpl @Inject constructor(
    private val networkService: NetworkService
) : RemoteSource {

    override suspend fun getRepos(owner: String): List<GithubRepoRes> {
        return networkService.getRepos(owner)
    }
}