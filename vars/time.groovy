def call() {
    return sh(script: 'date +"%H:%M:%S"', returnStdout: true).trim()
}