pipeline {
    agent any

    stages {
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
