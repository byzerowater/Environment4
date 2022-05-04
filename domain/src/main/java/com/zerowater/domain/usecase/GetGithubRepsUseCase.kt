package com.zerowater.domain.usecase

import com.zerowater.domain.repository.GithubRepository

class GetGithubRepsUseCase(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(
        owner: String
    ) = githubRepository.getRepos(owner)
}