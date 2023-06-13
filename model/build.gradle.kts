@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = Config.nameModel
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
    }


}

dependencies {
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}