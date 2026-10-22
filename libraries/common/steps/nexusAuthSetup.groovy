def call(Map config = [:]) {
  
    def nexusUrl = config.nexusUrl ?: 'http://localhost:8081'
    def credentialsId = config.credentialsId ?: 'nexus-credentials-id'
    
    def nexusDomain = nexusUrl.replaceAll('https?://', '')

    echo "Setting up Nexus Authentication for Domain: ${nexusDomain}"
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
        script {
            String rawCreds = "${NEXUS_USER}:${NEXUS_PASS}"
            String base64Auth = rawCreds.bytes.encodeBase64().toString()

            sh """
                echo "registry=${nexusUrl}/repository/npm-all/" > .npmrc
                echo "always-auth=true" >> .npmrc
                echo "//${nexusDomain}/repository/npm-all/:_auth=${base64Auth}" >> .npmrc
                echo "//${nexusDomain}/repository/npm-private/:_auth=${base64Auth}" >> .npmrc
            """
        }
    }
}
