def call(Map config = [:]) {
    // extract repo name from the git URL, e.g. https://github.com/you/BoardGame.git -> BoardGame
    def repoName = env.GIT_URL?.tokenize('/')?.last()?.replace('.git', '') ?: env.JOB_NAME

    def projectName = config.projectName ?: repoName
    def projectKey   = config.projectKey  ?: repoName

    def scannerHome = tool 'SonarScanner'
    withSonarQubeEnv('sonar-server') {
        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectName=${projectName} -Dsonar.projectKey=${projectKey} -Dsonar.java.binaries=."
    }
}
