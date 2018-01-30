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
                bat "javac"
                #archiveArtifacts artifacts: '*.java', fingerprint: true
                echo "Done!"
            }
        }
    }
}
