package com.example.testapppattern.core.di.app

data class AGlobal(
    val appPackageName: String,
    val startedAtMillis: Long = System.currentTimeMillis(),
)
