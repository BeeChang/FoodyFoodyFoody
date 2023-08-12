
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = Config.nameData
    compileSdk = Config.compileSdk
    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
    }
}



dependencies {
    implementation(project(":model"))
    implementation(project(":network"))
    implementation(project(":database"))
    implementation(project(":test"))

    testImplementation(project(":network"))
    testImplementation(project(":database"))
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.coroutines)
    testImplementation(libs.robolectric)

    implementation(libs.sandwich)
    implementation(libs.coroutines)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

}