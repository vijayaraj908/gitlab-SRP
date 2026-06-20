pipeline {
    agent any
    
    environment {
        // This is the exact path verified from your EC2 instance
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        // This ensures Maven uses the Java 21 compiler
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                // Now Maven will use the environment variables defined above
                sh 'mvn clean package'
            }
        }
    }
}
