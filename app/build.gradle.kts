plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(DefaultConfig.compileSdk)
    buildToolsVersion = DefaultConfig.buildToolVersion

    defaultConfig {
        applicationId = DefaultConfig.appId
        minSdkVersion(DefaultConfig.minSdk)
        targetSdkVersion(DefaultConfig.targetSdk)
        versionCode = DefaultConfig.versionCode
        versionName = DefaultConfig.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lintOptions {
        disable("MissingTranslation")
    }

    packagingOptions {
        exclude("/META-INF/LICENCE")
        exclude("/META-INF/LICENSE.txt")
        exclude("/META-INF/NOTICE")
        exclude("/META-INF/NOTICE.txt")
        exclude("/NOTICE")
        exclude("/NOTICE.txt")
        exclude("/LICENSE.txt")
        exclude("/LICENSE")
        exclude("META-INF/kotlinx-io.kotlin_module")
        exclude("META-INF/atomicfu.kotlin_module")
        exclude("META-INF/kotlinx-coroutines-io.kotlin_module")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")

    //material_design
    implementation("com.google.android.material:material:${Versions.material}")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")

    //retrofit2
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxjava3Adp}")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")

    //rxjava & rxkotlin
    implementation("io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}")
    implementation("io.reactivex.rxjava3:rxkotlin:${Versions.rxkotlin}")
    implementation("com.github.akarnokd:rxjava3-extensions:${Versions.rxjava3Ext}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.glide}")
    implementation("com.google.firebase:firebase-messaging:21.0.1")
    annotationProcessor("com.github.bumptech.glide:compiler:${Versions.glide}")

    // log
    implementation("com.orhanobut:logger:${Versions.logger}")
    implementation("com.jakewharton.timber:timber:${Versions.timber}")

    //test
    testImplementation("androidx.test.ext:junit:${Versions.androidJunit}")
    testImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
}
