def call() {
    return sh(script: 'date +"%d-%b-%Y"', returnStdout: true).trim()
}