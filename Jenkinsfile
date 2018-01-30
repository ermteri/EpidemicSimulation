pipeline {
    agent { label 'master'}
    stages {
        stage("Checkout") {
            steps {
                git 'https://github.com/ermteri/EpidemicSimulation.git'
                echo "Checkout done"
            }
        }
        stage("Test") {
            steps {
                bat "make check||true"
                junit '**/target/*.xml'
                // archiveArtifacts artifacts: '*.java', fingerprint: true
                echo "Done!"
            }
        }
    }
}
