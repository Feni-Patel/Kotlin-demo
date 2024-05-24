package com.First.Kotlindemo.mapper

import com.First.Kotlindemo.dto.SucceededJobsDTO
import com.First.Kotlindemo.model.SucceededJobs
import org.springframework.stereotype.Component

@Component
class SucceededJobsMapper {

    fun toDTO(entity: SucceededJobs): SucceededJobsDTO {
        return SucceededJobsDTO(entity.id, entity.jobDetails, entity.succeeded)
    }

    fun toEntity(dto: SucceededJobsDTO): SucceededJobs {
        return SucceededJobs(dto.id, dto.jobDetails, dto.succeeded)
    }
}
