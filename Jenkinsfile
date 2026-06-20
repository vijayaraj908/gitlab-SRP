pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Ensure the workspace is clean and run Maven
                sh 'mvn clean package'
            }
        }
    }
}
