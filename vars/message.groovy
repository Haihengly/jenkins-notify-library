def call() {
    return sh(script: "git log -1 --pretty=format:'%s'", returnStdout: true).trim()
}