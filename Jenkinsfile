pipeline {
    agent any
    tools {
        jdk 'JDK21' // This must match the name you gave in Jenkins Tools
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
