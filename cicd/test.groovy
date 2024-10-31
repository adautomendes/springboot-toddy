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

        stage('Run Unit Tests') {
            steps {
                script {
                    sh 'mvn test -Dtest=ToddyServiceTest'
                }
            }
        }

        stage('Run Integration Tests') {
            steps {
                script {
                    sh 'mvn test -Dtest=ToddyControllerTest -Dtoddy.size.min=${toddySizeMin} -Dtoddy.size.max=${toddySizeMax}'
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