plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "gsg.corp.ruterito.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val stringType = "String"
    buildTypes {
        getByName("release") {
            buildConfigField(stringType, "BASE_URL", properties["URL_BASE_PROD"].toString())
        }
        create("qa") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
            buildConfigField(stringType, "BASE_URL", properties["URL_BASE_PROD"].toString())
        }
        getByName("debug") {
            buildConfigField(stringType, "BASE_URL", properties["URL_BASE_DEV"].toString())
            buildConfigField(stringType, "MAPS_API_KEY", properties["MAPS_API_KEY"].toString())
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("**/attach_hotspot_windows.dll")
        resources.excludes.add("META-INF/licenses/ASM")
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    debugImplementation(Compose.uiToolingPreviewDebug)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.matIcon)
    implementation(Compose.matIconExt)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.splashScreen)
    //implementation(Accompanist.navigation)
    //implementation(Accompanist.animationNav)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)
    /* -- include all modules if you need -- */
    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))



    implementation(project(Modules.onboardingData))
    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.onboardingPresentation))

    /* -- end include -- */
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(Coil.coilCompose)

    implementation(Google.material)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
}
