pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/<your-username>/student-registration-portal.git'
            }
        }
        stage('Build') {
            steps {
                // Assuming you use Maven. If using Gradle, use 'gradle build'
                sh 'mvn clean package'
            }
        }
    }
}
