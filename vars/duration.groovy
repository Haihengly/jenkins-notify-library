def call() {
    def durationMs = currentBuild.duration
    def durationStr = currentBuild.durationString
    echo "Build took: ${durationStr} (${durationMs} ms)"
}