def call(String language) {
    if (language == 'maven') {
        sh "mvn package"
    } else if (language == 'python') {
        sh """
            . venv/bin/activate
            pip install build
            python3 -m build
        """
    } else if (language == 'nodejs') {
        sh "npm pack"
    } else {
        error "Unsupported language: ${language}"
    }
}
