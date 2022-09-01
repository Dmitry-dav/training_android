package com.example.practice.di

import com.example.practice.domain.repository.UserRepository
import com.example.practice.domain.usecase.AuthorizationUseCase
import com.example.practice.domain.usecase.RegistrationUseCase
import com.example.practice.domain.usecase.RestorePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModuleViewModel {

    @Provides
    fun provideAuthorizationUseCase(userRepository: UserRepository): AuthorizationUseCase {
        return AuthorizationUseCase(userRepo = userRepository)
    }

    @Provides
    fun provideRegistrationUseCase(userRepository: UserRepository): RegistrationUseCase {
        return RegistrationUseCase(userRepo = userRepository)
    }

    @Provides
    fun provideRestorePasswordUseCase(userRepository: UserRepository): RestorePasswordUseCase {
        return RestorePasswordUseCase(userRepo = userRepository)
    }
}