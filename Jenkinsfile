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
                bat "C:/Users/Torsten/python/python.exe web_crawler_org.py"
                archiveArtifacts artifacts: '*.py', fingerprint: true
                echo "Done!"
            }
        }
    }
}
