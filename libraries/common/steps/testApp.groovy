def call(String language) {
    if (language == 'maven') {

        sh "mvn test"

    } else if (language == 'python') {

        sh """
            . venv/bin/activate

            pip install pytest

            if find . -type f \\( -name "test_*.py" -o -name "*_test.py" \\) | grep -q .; then
                echo "Python test files found. Running pytest..."
                pytest
            else
                echo "No Python test files found. Skipping tests."
            fi
        """

    } else if (language == 'nodejs') {

        sh "npm test"

    } else {

        error "Unsupported language: ${language}"
    }
}
