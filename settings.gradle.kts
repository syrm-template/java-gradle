@file:Suppress("UnstableApiUsage")

rootProject.name = "java-gradle-template"

rootProject.children.forEach { it.buildFileName = it.name + ".gradle.kts" }

pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/spring-plugin")
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        val aliYun: (PasswordCredentials).() -> Unit = {
            username = providers.gradleProperty("MAVEN-REPO-AL-USERNAME").getOrElse("")
            password = providers.gradleProperty("MAVEN-REPO-AL-PASSWORD").getOrElse("")
        }
        maven {
            name = "阿里云效仓库"
            setUrl("https://packages.aliyun.com/maven/repository/2312424-release-8x1TrK/")
            credentials(aliYun)
            mavenContent { releasesOnly() }
        }
        maven {
            name = "阿里云效镜像仓库"
            setUrl("https://packages.aliyun.com/maven/repository/2312424-snapshot-SeVvm7/")
            credentials(aliYun)
            mavenContent { snapshotsOnly() }
        }
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/spring/")
        mavenCentral()
    }

    @Suppress("SpellCheckingInspection")
    versionCatalogs {
        create("libs") {
            library("jetbrainsAnnotations", "org.jetbrains:annotations:24.0.0")
            library("lombok", "org.projectlombok:lombok:1.18.26")
            library("slf4j", "org.slf4j:slf4j-api:2.0.6")
            library("slf4jJul", "org.slf4j:jul-to-slf4j:2.0.5")
            library("logback", "ch.qos.logback:logback-classic:1.4.5")
        }
        create("jakarta") {
            library("inject", "jakarta.inject:jakarta.inject-api:2.0.1")
            library("annotationApi", "jakarta.annotation:jakarta.annotation-api:2.1.1")
            library("validationApi", "jakarta.validation:jakarta.validation-api:3.0.2")
            library("servletApi", "jakarta.servlet:jakarta.servlet-api:6.0.0")
            library("transactionApi", "jakarta.transaction:jakarta.transaction-api:2.0.1")
        }
        create("testLibs") {
            val junitVersion = "5.9.2"
            library("junitApi", "org.junit.jupiter:junit-jupiter-api:${junitVersion}")
            library("junitEngine", "org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
            library("junitPlatformLauncher", "org.junit.platform:junit-platform-launcher:1.9.2")
        }
    }

}
