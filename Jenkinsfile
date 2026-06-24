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
                    }

                    // 2. Restart the systemd service
                    // Note: Ensure the 'jenkins' user has sudo privileges for systemctl
                    sh 'sudo systemctl restart srp-app'
                    
                    echo 'Waiting for service to stabilize...'
                    sleep 10
                    
                    echo 'Deployment complete.'

                    sh 'sudo pkill -f demo-0.0.1-SNAPSHOT.jar || true'
                    sh 'nohup java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=8081 > app.log 2>&1 &'
                }
            }
        }
    }
    post
    {
        success
        {
            mail to: 'vijayaraj.innovate@gmail.com',
                 subject: "Build success : ${CurrentBuild.fullDisplayName}",
                 body: "The build was successful and the SRP application is Live now."
        }
        failure
        {
            mail to: 'vijayaraj.innovate@gmail.com',
                 subject: "Build Failed! : $(CurrentBuild.fullDisplayName}",
                 body: "The build has been failed. Please check the logs."
        }
    }
}
