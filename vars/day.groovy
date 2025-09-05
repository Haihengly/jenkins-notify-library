def call() {
    return sh(script: 'date +"%A"', returnStdout: true).trim()
}