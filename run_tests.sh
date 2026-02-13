#!/bin/bash

# Script to run the compiled test files

JUNIT_JAR="junit-platform-console-standalone-1.9.3.jar"

if [ ! -f "${JUNIT_JAR}" ]; then
    echo "Error: JUnit JAR not found. Please run './compile_tests.sh' first."
    exit 1
fi

echo "Running tests..."
echo "================"

java -jar ${JUNIT_JAR} --class-path . --scan-class-path --disable-ansi-colors

echo ""
echo "================"
echo "Test execution complete!"