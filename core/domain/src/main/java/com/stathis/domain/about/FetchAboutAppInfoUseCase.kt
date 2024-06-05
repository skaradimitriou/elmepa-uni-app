package com.stathis.domain.about

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.GeneralAppInfoRepository
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAboutAppInfoUseCase @Inject constructor(
    private val repo: GeneralAppInfoRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchAboutAppInfo()
}