package com.First.Kotlindemo.dto

data class SucceededJobsDTO(
    val id: Long,
    val jobDetails: String,
    val succeeded: Boolean
)
