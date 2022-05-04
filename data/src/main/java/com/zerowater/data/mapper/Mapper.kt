package com.zerowater.data.mapper

import com.zerowater.data.model.GithubRepoRes
import com.zerowater.domain.model.GithubRepo

class Mapper {

    fun mapperToGithubRepo(githubRepos: List<GithubRepoRes>): List<GithubRepo> {
        return githubRepos.map { GithubRepo(it.name, it.url) }
    }
}