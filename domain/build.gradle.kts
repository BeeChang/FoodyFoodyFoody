
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = Config.nameDomain
    compileSdk = Config.compileSdk
    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":data"))
    implementation(project(":test"))

    implementation(libs.coroutines)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
