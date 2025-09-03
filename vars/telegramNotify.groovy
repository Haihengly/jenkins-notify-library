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
def localIP() {
    return sh(script: "hostname -I | awk '{print $1}'", returnStdout: true).trim()

}

def notify(String status) {
    def date = date()
    def day = day()
    def time = time()
    def buildTime = duration()           
    def auth = author()
    def msg = message()
    def lclIP = localIP()


    // def message = "${status} - Job: ${env.JOB_NAME} #${env.BUILD_NUMBER} - Duration: ${buildTime} - Time: ${now}"

    def telegramText = """
    ------------------------------

    PROJECT : ${env.PROJECT_NAME}
    STATUS : ${status}
    BUILD VERSION : ${env.BUILD_VERSION}

    ------------------------------

    Date : ${date}
    Day : ${day}
    Time : ${time}

    ------------------------------

    COMMIT BY: ${auth}
    MESSAGE: ${msg}
    DURATION: ${buildTime}
    LOCAL IP: ${lclIP}
    SUBDOMAIN: ${env.SUBDOMAIN}
    """.trim()

    sh """
    curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
    -d chat_id="${env.CHAT_ID}" \
    -d text="${telegramText}"
    """
}