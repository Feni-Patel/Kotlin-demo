package com.First.Kotlindemo.controller

import com.First.Kotlindemo.model.SucceededJobs
import com.First.Kotlindemo.repository.SucceededJobsRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SucceededJobsController(private val succeededJobsRepository: SucceededJobsRepository) {

    @GetMapping("/succeeded-jobs")
    fun getAllSucceededJobs(): ResponseEntity<List<SucceededJobs>> {
        val jobs = succeededJobsRepository.findAll()
        return ResponseEntity.ok(jobs)
    }

    @PostMapping("/succeeded-jobs")
    fun createNewSucceededJob(@Valid @RequestBody job: SucceededJobs): ResponseEntity<SucceededJobs> {
        val savedJob = succeededJobsRepository.save(job)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob)
    }

    @GetMapping("/succeeded-jobs/{id}")
    fun getSucceededJobById(@PathVariable(value = "id") jobId: Long): ResponseEntity<SucceededJobs> {
        return succeededJobsRepository.findById(jobId).map { job ->
            ResponseEntity.ok(job)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/succeeded-jobs/{id}")
    fun updateSucceededJobById(
        @PathVariable(value = "id") jobId: Long,
        @Valid @RequestBody newJob: SucceededJobs
    ): ResponseEntity<SucceededJobs> {
        return succeededJobsRepository.findById(jobId).map { existingJob ->
            val updatedJob = existingJob.copy(jobDetails = newJob.jobDetails, succeeded = newJob.succeeded)
            ResponseEntity.ok().body(succeededJobsRepository.save(updatedJob))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/succeeded-jobs/{id}")
    fun deleteSucceededJobById(@PathVariable(value = "id") jobId: Long): ResponseEntity<Void> {
        return succeededJobsRepository.findById(jobId).map { job ->
            succeededJobsRepository.delete(job)
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity.notFound().build())
    }
}
