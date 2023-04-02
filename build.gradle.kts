import java.nio.charset.StandardCharsets

plugins {
    java
}

allprojects {
    group = "cn.template"
    version = "1.0-SNAPSHOT"
}

subprojects {
    java {
        toolchain {
            // 工具链语言版本必须和 运行 gradle 所用 JDK 版本一致
            languageVersion.set(JavaLanguageVersion.of(17))
        }
        sourceSets {
            main {
                java { setSrcDirs(listOf("java")) }
                resources { setSrcDirs(listOf("resources")) }
            }
            test {
                java { setSrcDirs(listOf("test")) }
                resources { setSrcDirs(listOf("testResources")) }
            }
        }
        withSourcesJar()
        withJavadocJar()
    }

    configurations.all {
        resolutionStrategy.cacheDynamicVersionsFor(1, TimeUnit.DAYS)
        resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.DAYS)
    }

    dependencies {
        implementation(libs.slf4j)
        implementation(libs.slf4jJul)
        runtimeOnly(libs.logback)

        compileOnly(libs.lombok)
        annotationProcessor(libs.lombok)
        testCompileOnly(libs.lombok)
        testAnnotationProcessor(libs.lombok)

        testImplementation(testLibs.junitApi)
        testRuntimeOnly(testLibs.junitEngine)
        testRuntimeOnly(testLibs.junitPlatformLauncher)
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.name()
    }

    @Suppress("SpellCheckingInspection")
    tasks.withType<Javadoc> {
        options {
            this as StandardJavadocDocletOptions
            encoding = StandardCharsets.UTF_8.name()
            charset(StandardCharsets.UTF_8.name())
            addBooleanOption("Xdoclint:none", true)
            addStringOption("Xmaxwarns", "1")
        }
    }

}
