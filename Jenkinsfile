stage('Deploy') {
            steps {
                script {
                    // NOTICE: 'sudo' has been removed from these commands
                    def containerStatus = sh(script: 'docker ps -q -f name=sql_server_container', returnStdout: true).trim()
                    
                    if (!containerStatus) {
                        echo 'Starting SQL Server container...'
                        // NOTICE: 'sudo' removed here as well
                        sh 'docker start sql_server_container || docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Vijaysql@16" -p 1433:1433 --name sql_server_container -d mcr.microsoft.com/mssql/server:2022-latest'
                    }

                    // Deploy the app
                    sh '''
                    pkill -f demo-0.0.1-SNAPSHOT.jar || true
                    
                    nohup java -jar target/demo-0.0.1-SNAPSHOT.jar \
                    --server.port=8081 \
                    > /home/ubuntu/app.log 2>&1 &
                    '''
                }
            }
        }
