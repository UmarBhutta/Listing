plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(project(":feature:users_list:api"))
    implementation(project(":domain:network"))
    implementation(libs.koin.core)
    implementation(libs.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}
