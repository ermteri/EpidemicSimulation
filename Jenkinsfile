pipeline {
    agent {
        docker {
            image 'maven:3.5.2-jdk-9'
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B clean package' 
                //sh 'mvn --version'
            }
        }
    }
}
