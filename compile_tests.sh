#!/bin/bash

# Script to compile the test files
# Requires JUnit 5 (Jupiter) JAR files

echo "Compiling Project.java and test files..."

# Download JUnit 5 JARs if not present
if [ ! -f "junit-platform-console-standalone-1.9.3.jar" ]; then
    echo "Downloading JUnit 5 standalone JAR..."
    wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar
fi

JUNIT_JAR="junit-platform-console-standalone-1.9.3.jar"

# Compile the main source files
echo "Compiling project.java..."
javac project.java

if [ $? -ne 0 ]; then
    echo "Error: Failed to compile project.java"
    exit 1
fi

# Compile the test files
echo "Compiling test files..."
javac -cp ".:${JUNIT_JAR}" ProjectTest.java RockPaperScissorsTest.java

if [ $? -ne 0 ]; then
    echo "Error: Failed to compile test files"
    exit 1
fi

echo "Compilation successful!"
echo "Run './run_tests.sh' to execute the tests"