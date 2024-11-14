pipeline {
    agent any
    stages {
        stage('Clone Repo') {
            steps {
                script {
                    git branch: 'main', url: 'https://github.com/adautomendes/springboot-toddy.git'
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
                    sh 'mvn pmd:check -Dpmd.failOnViolation=true -Dpmd.printFailingErrors=true'
                }
            }
        }
    }

    post {
        success {
            println 'Build was successful!'
            archiveArtifacts artifacts: '**/*.xml', allowEmptyArchive: true
        }
        failure {
            println 'Build failed.'
        }
    }
}