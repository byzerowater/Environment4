package com.zerowater.domain.usecase

import com.zerowater.domain.repository.GithubRepository

class GetGithubReposUseCase(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(
        owner: String
    ) = githubRepository.getRepos(owner)
}