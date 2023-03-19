@file:Suppress("UnstableApiUsage")

rootProject.name = "java-gradle-template"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/spring/")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            @Suppress("SpellCheckingInspection")
            library("lombok", "org.projectlombok:lombok:1.18.26")
            library("jetbrainsAnnotations", "org.jetbrains:annotations:24.0.0")
            library("slf4j", "org.slf4j:slf4j-api:2.0.6")
            library("slf4jJul", "org.slf4j:jul-to-slf4j:2.0.5")
            library("logback", "ch.qos.logback:logback-classic:1.4.5")
        }

        // 测试环境依赖
        create("testLibs") {
            version("junitVersion", "5.9.2")
            library("junitApi", "org.junit.jupiter", "junit-jupiter-api").versionRef("junitVersion")
            library("junitEngine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junitVersion")
            library("junitPlatformLauncher", "org.junit.platform:junit-platform-launcher:1.9.2")
        }
    }
}
