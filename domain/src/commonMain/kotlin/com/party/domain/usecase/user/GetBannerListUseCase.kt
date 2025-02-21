package com.party.domain.usecase.user

import com.party.domain.repository.BannerRepository

class GetBannerListUseCase(
    private val bannerRepository: BannerRepository
){
    suspend operator fun invoke() = bannerRepository.getBannerList()
}