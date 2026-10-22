def call(String language) {
    if (language == 'maven') {
        sh "mvn test"
    } else if (language == 'python') {
        sh """
            . venv/bin/activate
            pip install pytest
            pytest
        """
    } else if (language == 'nodejs') {
        sh "npm test"
    } else {
        error "Unsupported language: ${language}"
    }
}
