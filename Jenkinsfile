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
                script {
                    // 1. Ensure Docker is running
                    def containerStatus = sh(script: 'docker ps -q -f name=sql_server_container', returnStdout: true).trim()
                    if (!containerStatus) {
                        echo 'Starting SQL Server container...'
                        sh 'docker start sql_server_container || docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Vijaysql@16" -p 1433:1433 --name sql_server_container -d mcr.microsoft.com/mssql/server:2022-latest'
                    }

                    // 2. Deployment
                    sh '''
                    # Stop any old process
                    pkill -f demo-0.0.1-SNAPSHOT.jar || true
                    
                    # Prevent Jenkins from killing the process
                    export BUILD_ID=dontKillMe
                    
                    # Run and log to the workspace so we can troubleshoot if it fails
                    nohup java -jar target/demo-0.0.1-SNAPSHOT.jar \
                    --server.port=8081 > app.log 2>&1 &
                    '''
                }
            }
        }
    }
}
