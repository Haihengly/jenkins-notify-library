def call() {
    return sh(script: "git log -1 --pretty=format:'%an'", returnStdout: true).trim()
}