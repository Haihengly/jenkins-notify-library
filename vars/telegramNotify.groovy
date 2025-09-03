def getFormattedTimestamp() {
    return sh(script: 'date +"%d-%b-%Y, %a %H:%M:%S"', returnStdout: true).trim()
}
def author = sh(script: "git log -1 --pretty=format:'%an'", returnStdout: true).trim()
def message = sh(script: "git log -1 --pretty=format:'%s'", returnStdout: true).trim()


def notify(String status) {
    def buildTime = duration()               // Call your shared lib step
    def now = getFormattedTimestamp()        // Get formatted timestamp

    // def message = "${status} - Job: ${env.JOB_NAME} #${env.BUILD_NUMBER} - Duration: ${buildTime} - Time: ${now}"

    def telegramText = """
        Status : ${status}
    👤 Commit by: ${author}
    🔖 Commit: ${hash}
    📝 Message: ${message}
    📦 Job: ${env.JOB_NAME} #${env.BUILD_NUMBER}
    📅 Duration: ${buildTime}
    📅 Time: ${now}
    """.trim()

    sh """
    curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
    -d chat_id="${env.CHAT_ID}" \
    -d text="${telegramText}"
    """
}