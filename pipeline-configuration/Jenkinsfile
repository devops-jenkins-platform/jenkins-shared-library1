@Library('Jenkins-shared-Library') _

pipeline {
    agent any
    environment {
        IMAGE_NAME = "sirishakaruturi/myapp"
        IMAGE_TAG = "${BUILD_NUMBER}"
        HARBOR_URL = "docker.io"
        HARBOR_CREDENTIALS = "dockerhub-credentials"
    }
    stages {
        stage('Git Checkout') {
           steps {
               gitCheckout('main', 'https://github.com/devops-jenkins-platform/simple-webapp-flask', 'github-creds')
           }
        }
        stage('Setup Tools') {
           steps {
              setupTool("python")
           }
        }
        stage('Compile') {
            steps {
                compileApp("python")
            }
        }
        stage('Test') {
            steps {
                testApp("python")
            }
        }
        stage('File System Scan') {
            steps {
                container('trivy'){
                    trivyScan(scanType: 'fs', target: '.', reportName: 'trivy-fs-report.html')
                }
                
            }
        }
        stage('SonarQube Analysis') {
            steps {
                SonarAnalysis()
            }
        }

        stage('Quality Gate') {
            steps {
                QualityGate()
            }
        }
        stage('Publish To Nexus') {
            steps {
                PublishToNexus()
            }
        }
        stage('Build') {
            steps {
                buildApp("python")
            }
        }

        
        stage('Docker Build') {
            steps {
                dockerBuild(env.IMAGE_NAME,env.IMAGE_TAG)
            }
        }

        stage('Trivy Scan') {
            steps {
                trivyScan(scanType: 'image',target: "${env.IMAGE_NAME}:${env.IMAGE_TAG}",reportName: 'trivy-image-report.html')
            }
        }

        stage('Docker Push') {
            steps {
                pushImage(env.IMAGE_NAME, env.IMAGE_TAG, env.HARBOR_URL, env.HARBOR_CREDENTIALS)
            }
        }
    }
}
