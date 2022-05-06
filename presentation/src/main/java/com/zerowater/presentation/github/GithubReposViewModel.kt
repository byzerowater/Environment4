package com.zerowater.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerowater.domain.model.GithubRepo
import com.zerowater.domain.usecase.GetGithubReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(
    private val getGithubReposUseCase: GetGithubReposUseCase
) : ViewModel() {

    private val _githubRepos = MutableStateFlow(emptyList<GithubRepo>())
    val githubRepos: StateFlow<List<GithubRepo>> = _githubRepos

    fun getGithubRepositories(owner: String) {
        viewModelScope.launch {
            getGithubReposUseCase(owner)
                .catch {
                    it.printStackTrace()
                }.collect {
                    _githubRepos.value = it
                }
        }
    }
}