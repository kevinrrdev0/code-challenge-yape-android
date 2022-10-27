package gsg.corp.onboarding_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.onboarding_domain.model.UserInfo
import gsg.corp.onboarding_domain.repository.OnBoardingRepository

class VerificationUser(
    private val repository: OnBoardingRepository
) {
    suspend operator fun invoke(userName:String, userPassword:String): Resource<UserInfo> {
        return repository.verificationUser(userName,userPassword)
    }
}