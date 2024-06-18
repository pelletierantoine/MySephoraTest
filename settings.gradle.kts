rootProject.name = "My Sephora Test"
include(
    ":app",
    ":data",
    ":domain"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}
include(":app_data")
