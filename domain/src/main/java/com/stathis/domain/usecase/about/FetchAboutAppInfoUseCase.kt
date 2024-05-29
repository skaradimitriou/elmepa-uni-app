package com.stathis.domain.usecase.about

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.GeneralAppInfoRepository
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAboutAppInfoUseCase @Inject constructor(
    private val repo: GeneralAppInfoRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchAboutAppInfo()
}