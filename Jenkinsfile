pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                pkill -f demo-0.0.1-SNAPSHOT.jar || true

                nohup java -jar target/demo-0.0.1-SNAPSHOT.jar \
                --server.port=8081 \
                > /home/ubuntu/app.log 2>&1 &
                '''
            }
        }
    }
}
