def call(Map config = [:]) {
    def mavenSettingsConfig = config.mavenSettingsConfig ?: 'nexus-config'
    def mavenTool           = config.maven ?: 'maven3'

    def mvnCmd = "mvn deploy"

    withMaven(globalMavenSettingsConfig: mavenSettingsConfig, maven: mavenTool) {
        sh mvnCmd 
    }
}
