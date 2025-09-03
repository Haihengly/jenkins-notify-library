def getFormattedTimestamp() {
    return sh(script: 'date +"%d-%b-%Y, %a %H:%M:%S"', returnStdout: true).trim()
}
return getFormattedTimestamp()
// def now = getFormattedTimestamp()
// echo "📅 Current time: ${now}"