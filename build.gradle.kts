import java.nio.charset.StandardCharsets

plugins {
    java
}

group = "cn.template"
version = "1.0-SNAPSHOT"

java {
    toolchain {
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
