pipeline{
    agent any
    triggers {
        pollSCM '*****'
    }
    stages{
        stage('Compile-Code'){
            steps{
                sh 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                sh 'mvn test'
            }
        }
        stage('Package-Application'){
            step{
                sh 'mvn package'
            }
        }
        stage('Build-Docker-Image'){
            step{
                sh 'docker build -t rjpraj/weather-predict-api:latest .'
            }
        }
        stage('Publish-Docker-Image'){
            step{
                sh 'docker push rjpraj/weather-predict-api:latest'
            }
        }
        stage('Deploy-Application'){
            step{
                sh 'java -jar ./target/weather-pridiction-api-0.0.1-SNAPSHOT.jar'
            }
        }

    }
}