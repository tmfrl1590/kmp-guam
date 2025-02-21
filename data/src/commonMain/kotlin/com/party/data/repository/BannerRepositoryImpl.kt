package com.party.data.repository

import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.core.domain.map
import com.party.core.domain.mapError
import com.party.data.network.banner.BannerRemoteSource
import com.party.domain.model.Banner
import com.party.domain.model.BannerItem
import com.party.domain.repository.BannerRepository

class BannerRepositoryImpl(
    private val bannerRemoteSource: BannerRemoteSource
): BannerRepository {
    override suspend fun getBannerList(): Result<Banner, DataErrorRemote<Unit>> {
        return bannerRemoteSource.getBannerList()
            .map { bannerDto ->
                Banner(
                    total = bannerDto.total,
                    banner = bannerDto.banner.map { item ->
                        BannerItem(  // ✅ BannerItem으로 변환해야 함
                            status = item.status,
                            createdAt = item.createdAt,
                            updatedAt = item.updatedAt,
                            id = item.id,
                            platform = item.platform,
                            title = item.title,
                            image = item.image,
                            link = item.link
                        )
                    }
                )
            }
            .mapError { error ->
                DataErrorRemote.BadRequest(response = Unit) // 실패 시 Unit을 반환
            }
    }
}