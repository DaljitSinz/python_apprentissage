pipeline {
    agent any
    stages {
        stage('GIT') {
            steps {
                git credentialsId: '49412063-b10f-455c-bffa-fd9c63e3e925', url: 'http://githuben.intranet.mckinsey.com/ebs-project/ebs-fin'
                sh 'echo hello'
                sh 'touch trry.txt'
                sh 'touch .txt'

                sh '''git branch
                cmtid=$(git log --format="%H" -n 1)
                git show --pretty="" --name-only $cmtid > trry.txt
                more trry.txt
                if  egrep -iq 'deploy_rollback.csv' "trry.txt"; then
                echo "The Deploy_rollback.csv file has been committed in current check-in"
                else
                echo "The Deploy_Rollback.csv file is not updated during Check-In"
                exit 1
                fi'''
                sh '''if  egrep -iq 'Approval Required : Yes' "deploy_rollback.csv"; then
                echo "YES" > app.txt
                grep -i '.com' "deploy_rollback.csv" > amail.txt
                cat "email.txt"
                else
                echo "NO" > app.txt
                fi'''
                sh 'cat app.txt'

            }
        }
        stage('Check') {
            environment {
                ROCK = readFile('app.txt')
                ROGER = readFile('amail.txt')
            }
            steps {
                echo "${env.ROCK}"
                echo "$ROCK"
                echo "${env.ROGER}"

                script
                {
                    if (DEV.trim().equals("NO"))
                    {
                        echo "Dependency on teams is not required"
                        echo "Deployment stage will be automatically triggered"
                    }
                    else {
                        echo "Email will be Triggerd for approval $date"
                        emailext (
                            body: """Mail Sent: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
                            Console output : ${env.RUN_DISPLAY_URL} ${env.GIT_BRANCH}""",
                            mimeType: 'text/html',
                            recipientProviders: [[$class: 'DevelopersRecipientProvider'], 
                            [$class: 'RequesterRecipientProvider']],
                            subject: "DEV-Deployment Failed : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                            replyTo: 'Daljit_Singh@external.mckinsey.com',
                            to: 'Daljit_Singh@external.mckinsey.com'

                            )
                    }

                }
            }
        }
        stage('Dev') {
            environment {
                DEV_ROCK = readFile('app.txt')
            }
            
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }   
            }
            steps {
                echo 'Started Dev Deployment'
            }
        }
        stage('BRANCH') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'origin/master') {
                        echo 'I only execute on the master branch'
                        } else {
                            echo 'I execute elsewhere'
                        }
                    }
                }
            }
            stage('UT') {
                steps {
                    script {
                        if (env.BRANCH_NAME == 'origin/master') {
                            echo 'I only execute on the master branch'
                            } else {
                                echo 'I execute elsewhere'
                            }
                        }
                    }
                }
            }
        }
