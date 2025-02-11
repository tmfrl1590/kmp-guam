package com.kmp.data.mapper

import com.kmp.data.dto.party.PartyListDto
import com.kmp.domain.model.party.PartyItem
import com.kmp.domain.model.party.PartyList
import com.kmp.domain.model.party.PartyTypeItem

object PartyMapper {

    fun mapperToPartyList(partyListDto: PartyListDto): PartyList{
        return PartyList(
            total = partyListDto.total,
            parties = partyListDto.parties.map {
                PartyItem(
                    id = it.id,
                    partyType = PartyTypeItem(
                        id = it.partyType.id,
                        type = it.partyType.type
                    ),
                    title = it.title,
                    content = it.content,
                    image = convertToImageUrl(it.image),
                    status = it.status,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    recruitmentCount = it.recruitmentCount,
                )
            }
        )
    }
}

fun convertToImageUrl(image: String?): String{
    return "https://partyguham.com/dev/api/$image"
}