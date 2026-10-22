def call(String imageName, String imageTag = "latest") {
    def fullImage = "${imageName}:${imageTag}"
    echo "Building Docker image: ${fullImage}"
    sh """
        docker build -t ${fullImage} .
    """
    echo "Docker image built successfully: ${fullImage}"
    return fullImage
}
