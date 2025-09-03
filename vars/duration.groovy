def call() {
    def elapsedMs = System.currentTimeMillis() - currentBuild.startTimeInMillis
    def elapsedSec = (elapsedMs / 1000) as int
    def mins = elapsedSec.intdiv(60)
    def secs = elapsedSec % 60
    def durationStr = "${mins} min ${secs} sec"

    return durationStr  // return instead of echo
}