plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.hibernate:hibernate-core:6.1.7.Final")
    implementation ("mysql:mysql-connector-java:8.0.18")
    implementation ("org.apache.logging.log4j:log4j-core:2.12.4")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.hibernate.validator:hibernate-validator-annotation-processor:8.0.1.Final")
    implementation("org.glassfish.expressly:expressly:5.0.0")
}

tasks.test {
    useJUnitPlatform()
}