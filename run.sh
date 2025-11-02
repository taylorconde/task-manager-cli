#!/bin/bash
./gradlew fatJar
java -jar app/build/libs/app-0.0.1-SNAPSHOT-all.jar
