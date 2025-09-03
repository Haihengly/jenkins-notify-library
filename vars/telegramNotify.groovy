def notify(String status) {
    sh """
    curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
    -d chat_id="${env.CHAT_ID}" \
    -d text="${status} - Job: ${env.JOB_NAME} #${env.BUILD_NUMBER} (date +%A)"
    """
}
