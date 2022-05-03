package com.zerowater.domain.repository

import com.zerowater.domain.model.GithubRepo

interface GithubRepository {
    suspend fun getRepos(owner: String): List<GithubRepo>
}