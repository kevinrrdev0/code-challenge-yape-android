package gsg.corp.onboarding_domain.use_case

data class OnBoardingUseCases(
    val verificationUser: VerificationUser,
    val getFoodRecipesUseCase: GetFoodRecipesUseCase,
    val filterRecipeUseCase: FilterRecipeUseCase,
    val getRecipeDetailUseCase: GetRecipeDetailUseCase
)