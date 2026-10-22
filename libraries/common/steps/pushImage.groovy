def call(String imageName, String imageTag, String harborUrl, String credentialsId) {
    def localImage = "${imageName}:${imageTag}"
    def harborImage = "${harborUrl}/${imageName}:${imageTag}"

    echo "Logging in to Harbor: ${harborUrl}"
    
    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'HARBOR_USERNAME',
            passwordVariable: 'HARBOR_PASSWORD'
        )
    ]) {

        sh """
            echo "\$HARBOR_PASSWORD" | docker login \
                ${harborUrl} \
                -u "\$HARBOR_USERNAME" \
                --password-stdin

            docker tag ${localImage} ${harborImage}
            docker push ${harborImage}
        """
    }

    echo "Docker image pushed successfully: ${harborImage}"
    return harborImage
}
