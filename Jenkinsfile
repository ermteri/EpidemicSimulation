pipeline {
    agent {
        docker {
            image 'maven:3.5.2-jdk-9-slim'
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B clean package'
                archive "target/**/*
                //sh 'mvn --version'
                junit 'target/**/*.xml'
            }

        }
    }
}
