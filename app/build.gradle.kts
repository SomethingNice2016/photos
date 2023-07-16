plugins {
    id(Dependencies.Plugins.ID.androidApplication)
    id(Dependencies.Plugins.ID.kotlinAndroid)
    id(Dependencies.Plugins.ID.kotlinKapt)
    id(Dependencies.Plugins.ID.daggerHilt)
    id(Dependencies.Plugins.ID.kotlinParcelize)
    id(Dependencies.Plugins.safeArgs)
    id(Dependencies.Plugins.ID.ksp) version Dependencies.Plugins.ID.kspVersion
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.somenthingnice.photos"


    compileSdkVersion(Config.Sdk.compileSdk)


    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = Config.Sdk.minSdk
        targetSdk = Config.Sdk.targetSdk
        vectorDrawables.useSupportLibrary = true
        versionName = Config.Versions.versionName
        versionCode = Config.Versions.versionCode
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.unsplash.com/\"")
        buildConfigField("String", "CLIENT_ID", "\"DeVUwRu5BS9Z54ehK4QCJhIufGXXZ8JrupHA4f8i8gA\"")

    }

    compileOptions {
        sourceCompatibility = Config.Java.version
        targetCompatibility = Config.Java.version
    }
    kotlinOptions {
        jvmTarget = Config.Java.version.toString()
    }

    flavorDimensions.add("type")


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dependencies {
        implementation(Dependencies.Hilt.hiltAndroid)
        implementation(Dependencies.Hilt.hiltNavigation)
        kapt(Dependencies.Hilt.hiltCompiler)

        implementation(Dependencies.Core.android)
        implementation(Dependencies.Core.appCompat)
        implementation(Dependencies.Google.material)
        implementation(Dependencies.Core.coordinatorLayout)
        implementation(Dependencies.Core.fragmentKtx)

        implementation(Dependencies.Lifecycle.viewModelKtx)
        implementation(Dependencies.Lifecycle.liveDataKtx)
        implementation(Dependencies.NavComponent.navigationFragment)
        implementation(Dependencies.NavComponent.navigationUi)
        implementation(Dependencies.NavComponent.runtime)

        implementation(Dependencies.Paging.common)
        implementation(Dependencies.Paging.runtime)

        implementation(Dependencies.Glide.common)

        implementation(Dependencies.Hilt.hiltAndroid)
        implementation(Dependencies.Core.swipeRefresh)

        implementation(Dependencies.Datastore.datastoreCore)
        implementation(Dependencies.Datastore.datastorePrefs)
        implementation(Dependencies.Moshi.moshi)

        implementation(Dependencies.Hilt.hiltAndroid)

        ksp(Dependencies.Room.roomCompiler)
        ksp(Dependencies.Moshi.moshiCodegen)

        implementation(Dependencies.Retrofit.retrofit)
        implementation(Dependencies.Retrofit.coroutinesAdapter)
        implementation(Dependencies.Retrofit.moshiConverter)
        implementation(Dependencies.OkHttp.okHttp)
        implementation(Dependencies.OkHttp.okHttpInterceptor)
        implementation(Dependencies.Moshi.moshi)

        testImplementation(Dependencies.Test.jUnit)
        androidTestImplementation(Dependencies.Test.mockkAgent)
        androidTestImplementation(Dependencies.Test.espresso)
    }
}
