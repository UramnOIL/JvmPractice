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
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
}