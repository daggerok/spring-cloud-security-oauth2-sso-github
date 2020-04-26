plugins {
  idea
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.ajoberstar.reckon")
  id("com.palantir.docker-run")
  id("org.springframework.boot")
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
}

val javaVersion = JavaVersion.VERSION_11

idea {
  project {
    languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(javaVersion)
  }
  module {
    isDownloadJavadoc = true
    isDownloadSources = true
  }
}

java.sourceCompatibility = javaVersion

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
  maven(uri("https://repo.spring.io/milestone"))
}

dependencies {
  implementation(kotlin("reflect"))
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.springframework.boot:spring-boot-starter-web")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")// { exclude(group = "org.junit.vintage", module = "junit-vintage-engine") }
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    showCauses = true
    showExceptions = true
    showStackTraces = true
    showStandardStreams = true
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "$javaVersion"
  }
}

tasks.withType(Wrapper::class.java) {
  gradleVersion = "${property("gradleVersion")}"
  distributionType = Wrapper.DistributionType.BIN
}

springBoot {
  buildInfo()
}

tasks {
  withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    launchScript()
  }
}

reckon {
  scopeFromProp()
  // stageFromProp()
  snapshotFromProp()
}

tasks {
  register("version") {
    println(project.version.toString())
  }
  register("status") {
    doLast {
      val status = grgit.status()
      status?.let { s ->
        println("workspace is clean: ${s.isClean}")
        if (!s.isClean) {
          if (s.unstaged.allChanges.isNotEmpty()) {
            println("""all unstaged changes: ${s.unstaged.allChanges.joinToString(separator = "") { i -> "\n - $i" }}""")
          }
        }
      }
    }
  }
}

defaultTasks("clean", "build")
