apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Security.crypto)
    "implementation"(Google.gson)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)

}