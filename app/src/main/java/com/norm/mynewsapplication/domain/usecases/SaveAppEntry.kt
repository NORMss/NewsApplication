package com.norm.mynewsapplication.domain.usecases

import com.norm.mynewsapplication.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}