package com.zerowater.data

import com.zerowater.data.mapper.Mapper
import com.zerowater.data.source.RemoteSource
import com.zerowater.domain.model.GithubRepo
import com.zerowater.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val defaultDispatcher: CoroutineDispatcher,
    private val mapper: Mapper
) : GithubRepository {

    override fun getRepos(owner: String): Flow<List<GithubRepo>> = flow {
        emit(
            mapper.mapperToGithubRepo(remoteSource.getRepos(owner))
        )
    }.flowOn(defaultDispatcher)


}