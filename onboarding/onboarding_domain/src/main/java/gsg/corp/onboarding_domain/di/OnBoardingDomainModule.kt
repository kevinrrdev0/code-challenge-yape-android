package gsg.corp.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import gsg.corp.onboarding_domain.repository.OnBoardingRepository
import gsg.corp.onboarding_domain.use_case.FilterRecipeUseCase
import gsg.corp.onboarding_domain.use_case.GetFoodRecipesUseCase
import gsg.corp.onboarding_domain.use_case.GetRecipeDetailUseCase
import gsg.corp.onboarding_domain.use_case.OnBoardingUseCases
import gsg.corp.onboarding_domain.use_case.VerificationUser


@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @ViewModelScoped
    @Provides
    fun provideDriverUseCases(
        repository: OnBoardingRepository
    ): OnBoardingUseCases {
        return OnBoardingUseCases(
            verificationUser = VerificationUser(repository),
            getFoodRecipesUseCase = GetFoodRecipesUseCase(repository),
            filterRecipeUseCase = FilterRecipeUseCase(),
            getRecipeDetailUseCase = GetRecipeDetailUseCase(repository)
        )
    }

}