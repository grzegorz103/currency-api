pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/grzegorz103/currency-api.git', branch: 'BRANCH_NAME'
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Package') {
            steps {
                sh "mvn package -DskipTests"
            }
        }

        stage('Build Docker image') {
            steps {
                sh "docker build -t currency-api:${BUILD_NUMBER} ."
            }
        }

    }
}
