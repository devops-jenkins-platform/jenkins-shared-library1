def call(String branch, String url, String credId) {
    git branch: branch, url: url, credentialsId: credId
}
