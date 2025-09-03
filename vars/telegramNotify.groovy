def notify(String status) {
    def buildTime = duration()  // call the other shared lib step

    sh """
    curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
    -d chat_id="${env.CHAT_ID}" \
    -d text="${status} - Job: ${env.JOB_NAME} #${env.BUILD_NUMBER} - Duration: ${buildTime}"
    """
}