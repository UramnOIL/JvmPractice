plugins {
    kotlin("jvm")
}

repositories {
    jcenter()
    maven {
        url = uri("http://repo.nukkitx.com/main/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
}