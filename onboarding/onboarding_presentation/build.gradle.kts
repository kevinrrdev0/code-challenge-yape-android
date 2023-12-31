apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.onboardingDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Google.mapCompose)
    "implementation"(Google.sdkMap)
}