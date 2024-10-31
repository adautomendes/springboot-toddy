pipeline {
    agent any
    stages {
        stage('Clone Repo') {
            steps {
                script {
                    git branch: 'main', url: 'https://github.com/adautomendes/springboot-helloworld.git'
                }
            }
        }

        stage('Clean') {
            steps {
                script {
                    sh 'mvn clean'
                }
            }
        }

        stage('Generate DOCs') {
            steps {
                script {
                    sh 'mvn verify'
                }
            }
        }
    }

    post {
        success {
            println 'Build was successful!'
            archiveArtifacts artifacts: '**/*.yaml', allowEmptyArchive: true
        }
        failure {
            println 'Build failed.'
        }
    }
}