pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                build job: "build", wait: true
            }
        }
        stage('Quality') {
            steps {
                build job: "quality", wait: true
            }
        }
        stage('Test') {
            steps {
                build job: "test", wait: true, parameters: [
                        string(name: 'toddySizeMin', value: "${toddySizeMin}"),
                        string(name: 'toddySizeMax', value: "${toddySizeMax}")
                ]
            }
        }
        stage('Documentation') {
            steps {
                build job: "documentation", wait: true
            }
        }
    }
}