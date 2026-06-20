pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                // We force Maven to use the Java 21 compiler directly
                sh '''
                export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
                export PATH=$JAVA_HOME/bin:$PATH
                mvn clean package -Dmaven.compiler.fork=true -Dmaven.compiler.executable=$JAVA_HOME/bin/javac
                '''
            }
        }
    }
}
