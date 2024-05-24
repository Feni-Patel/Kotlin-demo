package com.First.Kotlindemo.service

import com.First.Kotlindemo.dto.SucceededJobsDTO
import com.First.Kotlindemo.mapper.SucceededJobsMapper
import com.First.Kotlindemo.model.SucceededJobs
import com.First.Kotlindemo.repository.SucceededJobsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SucceededJobsService(
    private val succeededJobsRepository: SucceededJobsRepository,
    private val succeededJobsMapper: SucceededJobsMapper
) {
    fun getAllSucceededJobs(): List<SucceededJobsDTO> =
        succeededJobsRepository.findAll().map { succeededJobsMapper.toDTO(it) }

    fun createNewSucceededJob(dto: SucceededJobsDTO): SucceededJobsDTO {
        val entity = succeededJobsMapper.toEntity(dto)
        val savedJob = succeededJobsRepository.save(entity)
        return succeededJobsMapper.toDTO(savedJob)
    }

    fun getSucceededJobById(id: Long): SucceededJobsDTO? {
        val jobOptional: Optional<SucceededJobs> = succeededJobsRepository.findById(id)
        return if (jobOptional.isPresent) succeededJobsMapper.toDTO(jobOptional.get()) else null
    }

    fun updateSucceededJobById(id: Long, dto: SucceededJobsDTO): SucceededJobsDTO? {
        val existingJobOptional: Optional<SucceededJobs> = succeededJobsRepository.findById(id)
        if (existingJobOptional.isPresent) {
            val entity = succeededJobsMapper.toEntity(dto)
            entity.id = id // Ensure ID is set
            val updatedJob = succeededJobsRepository.save(entity)
            return succeededJobsMapper.toDTO(updatedJob)
        }
        return null
    }

    fun deleteSucceededJobById(id: Long) {
        succeededJobsRepository.deleteById(id)
    }

}
