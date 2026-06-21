pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Starting Deployment...'

                    // 1. Ensure SQL Server Docker container is running
                    def containerStatus = sh(script: 'docker ps -q -f name=sql_server_container', returnStdout: true).trim()
                    if (!containerStatus) {
                        echo 'Starting SQL Server container...'
                        sh '''
                        docker start sql_server_container || \
                        docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Vijaysql@16" \
                        -p 1433:1433 --name sql_server_container -d mcr.microsoft.com/mssql/server:2022-latest
                        '''
                    } else {
                        echo 'SQL Server container is already running.'
                    }

                    // 2. Deployment of the Spring Boot application
                    sh '''
                    # Stop any existing process to prevent port conflicts
                    pkill -f demo-0.0.1-SNAPSHOT.jar || true
                    
                    # Ensure the process lives on after Jenkins finishes
                    export BUILD_ID=dontKillMe
                    
                    # Use setsid to fully detach the process from the Jenkins agent
                    setsid java -jar target/demo-0.0.1-SNAPSHOT.jar \
                    --server.port=8081 > app.log 2>&1 &
                    
                    # Wait for the application to initialize
                    echo "Waiting for application to start..."
                    sleep 10
                    
                    # Output the end of the log to verify successful startup
                    echo "Checking application status in app.log..."
                    tail -n 20 app.log
                    '''
                    echo 'Deployment complete.'
                }
            }
        }
    }
}
