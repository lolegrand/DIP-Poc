plugins {
    kotlin("jvm") version "2.1.10"
    application
}

application {
    mainClass = "website.lolegrand.myapplication.MainKt"
}

dependencies {
    implementation(project(":library:api"))

    val buildVariant = project.findProperty("buildVariant")?.toString() ?: "dev"
    when(buildVariant) {
        "dev" -> {
            implementation(project(":library:dev"))
        }

        "prod" -> {
            implementation(project(":library:prod"))
        }

        "demo" -> {
            implementation(project(":library:demo"))
        }
    }

}