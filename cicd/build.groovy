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

        stage('Install Dependencies') {
            steps {
                script {
                    sh 'mvn install -DskipTests'
                }
            }
        }
    }

    post {
        success {
            println 'Build was successful!'
        }
        failure {
            println 'Build failed.'
        }
    }
}