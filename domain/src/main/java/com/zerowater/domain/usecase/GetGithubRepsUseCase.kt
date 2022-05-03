package com.zerowater.domain.usecase

import com.zerowater.domain.model.GithubRepo
import com.zerowater.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetGithubRepsUseCase(
    private val githubRepository: GithubRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(
        owner: String,
        scope: CoroutineScope,
        onResult: (List<GithubRepo>) -> Unit
    ) {
        scope.launch {
            onResult(withContext(dispatcher) {
                githubRepository.getRepos(owner)
            })
        }
    }
}