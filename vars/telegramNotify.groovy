def date() {
    return sh(script: 'date +"%d-%b-%Y"', returnStdout: true).trim()
}
def day() {
    return sh(script: 'date +"%A"', returnStdout: true).trim()
}
def time() {
    return sh(script: 'date +"%H:%M:%S"', returnStdout: true).trim()
}

def author() {
    return sh(script: "git log -1 --pretty=format:'%an'", returnStdout: true).trim()
}
def message() {
    return sh(script: "git log -1 --pretty=format:'%s'", returnStdout: true).trim()
}

def notify(String status) {

    def statusMessages = [
        SUCCESS: "BUILD SUCCESS ✅",
        FAILURE: "FAILED ❌",
        UNSTABLE: "UNSTABLE ⚠️"
    ]

    def displayStatus = statusMessages.get(status, status) // fallback to raw status
    def nowDate = date()
    def nowDay = day()
    def nowTime = time()
    def buildTime = duration()
    def auth = author()
    def msg = message()


    // def message = "${status} - Job: ${env.JOB_NAME} #${env.BUILD_NUMBER} - Duration: ${buildTime} - Time: ${now}"

    def telegramText = """
    -------------------------------

    🗂️ PROJECT : ${env.JOB_NAME}
    📊 STATUS : ${displayStatus}
    ⚙️ BUILD VERSION : ${env.BUILD_NUMBER}

    ------------------------------

    📅 Date : ${nowDate}
    🌓 Day : ${nowDay}
    ⏰ Time : ${nowTime}

    ------------------------------

    👤 COMMIT BY: ${auth}
    📩 MESSAGE: ${msg}
    ⏳ DURATION: ${buildTime}
    """

    sh """
    curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
    -d chat_id="${env.CHAT_ID}" \
    -d text="${telegramText}"
    """
}