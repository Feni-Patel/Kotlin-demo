package com.First.Kotlindemo.repository

import com.First.Kotlindemo.model.SucceededJobs
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SucceededJobsRepository : JpaRepository<SucceededJobs, Long>
