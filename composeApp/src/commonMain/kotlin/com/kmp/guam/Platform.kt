package com.kmp.guam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform