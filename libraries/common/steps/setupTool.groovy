// vars/setupTool.groovy
def call(String language) {
    if (language == 'maven') {
        env.MAVEN_HOME = tool 'maven3'
        env.PATH = "${env.MAVEN_HOME}/bin:${env.PATH}"
    } else if (language == 'nodejs') {
        env.NODEJS_HOME = tool 'nodejs'
        env.PATH = "${env.NODEJS_HOME}/bin:${env.PATH}"
    } else if (language == 'python') {
        echo "Python selected - using system python/pip, no tool setup needed"
    } else {
        error "Unsupported language: ${language}"
    }
}
