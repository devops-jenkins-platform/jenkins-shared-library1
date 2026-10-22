def call(String language) {

    if (language == 'maven') {

        def mavenSettingsConfig = 'nexus-config'
        def mavenTool = 'maven3'

        echo "Publishing Maven application to Nexus"

        withMaven(
            globalMavenSettingsConfig: mavenSettingsConfig,
            maven: mavenTool
        ) {
            sh "mvn deploy"
        }

    } else if (language == 'python') {

        echo "Publishing Python application to Nexus"

        sh """
            echo "Python package publishing will be implemented here"
        """

    } else if (language == 'nodejs') {

        echo "Publishing Node.js application to Nexus"

        sh """
            echo "Node.js package publishing will be implemented here"
        """

    } else {

        error "Unsupported language: ${language}"
    }
}
