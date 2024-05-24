package com.First.Kotlindemo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
data class SucceededJobs(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @field:NotBlank(message = "Job details must not be blank")
    val jobDetails: String,

    @field:NotNull(message = "Succeeded status must not be null")
    val succeeded: Boolean
)
