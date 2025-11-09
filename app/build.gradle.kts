import java.io.File

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "org.wmd"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.wmd"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val ruta = rootProject.file(".env")
        if (ruta.exists()) {
            try {
                //noinspection WrongGradleMethod
                File(".env").useLines { lineas ->
                    lineas.forEach {
                        val map = it.split("=")

                        print("[export] : " + map[0] + "=" + map[1] + '\n')
                        buildConfigField("String", map[0], "\"" + map[1] +"\"")
                    }
                }
            } catch (ignored: Exception) {

            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.webkit)
    implementation(libs.onesignal)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}