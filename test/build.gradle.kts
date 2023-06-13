@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
}

android {
    namespace = Config.nameTest
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
    }
}


dependencies {
    implementation(project(":model"))

    implementation(libs.coroutines)
    implementation(libs.coroutines.test)
    implementation(libs.junit)

}