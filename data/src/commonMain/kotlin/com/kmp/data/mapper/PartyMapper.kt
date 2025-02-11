package com.kmp.data.mapper

import com.kmp.data.dto.party.PartyListDto
import com.kmp.data.dto.party.RecruitmentListDto
import com.kmp.domain.model.party.PartyItem
import com.kmp.domain.model.party.PartyList
import com.kmp.domain.model.party.PartyTypeItem
import com.kmp.domain.model.party.RecruitmentItem
import com.kmp.domain.model.party.RecruitmentList
import com.kmp.domain.model.party.RecruitmentParty
import com.kmp.domain.model.party.RecruitmentPartyType
import com.kmp.domain.model.party.RecruitmentPosition

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

    fun mapperToRecruitmentList(recruitmentListDto: RecruitmentListDto): RecruitmentList{
        return RecruitmentList(
            total = recruitmentListDto.total,
            partyRecruitments = recruitmentListDto.partyRecruitments.map {
                RecruitmentItem(
                    id = it.id,
                    recruitingCount = it.recruitingCount,
                    recruitedCount = it.recruitedCount,
                    content = it.content,
                    createdAt = it.createdAt,
                    party = RecruitmentParty(
                        id = it.party.id,
                        title = it.party.title,
                        image = convertToImageUrl(it.party.image),
                        partyType = RecruitmentPartyType(
                            id = it.party.partyType.id,
                            type = it.party.partyType.type
                        )
                    ),
                    position = RecruitmentPosition(
                        id = it.position.id,
                        main = it.position.main,
                        sub = it.position.sub
                    )
                )
            }
        )
    }
}

fun convertToImageUrl(image: String?): String{
    return "https://partyguham.com/dev/api/$image"
}