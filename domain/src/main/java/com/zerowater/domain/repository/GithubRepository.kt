package com.zerowater.domain.repository

import com.zerowater.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    suspend fun getRepos(owner: String): Flow<List<GithubRepo>>
}