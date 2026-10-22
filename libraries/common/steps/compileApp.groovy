def call(String language) {
    if (language == 'maven') {
        sh "mvn compile"
    } else if (language == 'python') {
        sh """
            python3 -m venv venv
            . venv/bin/activate
            pip install --upgrade pip
        """
    } else if (language == 'nodejs') {
        sh "npm install"
    } else {
        error "Unsupported language: ${language}"
    }
}
