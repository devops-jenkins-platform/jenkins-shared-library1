def call(Map config = [:]) {
    def abortPipeline = config.abortPipeline != null ? config.abortPipeline : true
    def credentialsId  = config.credentialsId ?: 'sonar-creds'

    waitForQualityGate abortPipeline: abortPipeline, credentialsId: credentialsId
}
