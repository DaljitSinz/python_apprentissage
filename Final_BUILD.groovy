pipeline {
  agent any
  stages {
    stage('Checkout'){
      steps {
        sh '''if git ls-remote --exit-code -h "http://githuben.intranet.mckinsey.com/ebs-project/ebs-fin" | grep -q 'master';
        then
        echo "Git is available"
        else
        echo "Git Hub is not accessible to system"
        exit 1
        fi'''  
        sh 'touch commit_d.txt app.txt rep.txt email.txt jira.txt'
        git credentialsId: '49412063-b10f-455c-bffa-fd9c63e3e925', url: 'http://githuben.intranet.mckinsey.com/ebs-project/ebs-fin'
        sh '''if [ -f "dependency.txt" ]
        then
        echo "Dependency File found"
        else
        echo "Dependency File Not found"
        exit 1
        fi'''
        sh '''git branch
        cmtid=$(git log --format="%H" -n 1)
        git show --pretty="" --name-only $cmtid > commit_d.txt
        more commit_d.txt
        if  egrep -iq 'dependency.txt' "commit_d.txt"; then
        echo "The Depndency.txt file has been committed in current check-in"
        else
        echo "The Dependency.txt file is not updated during Check-In"
        exit 1
        fi'''
        sh '''if  egrep -iq 'Approval Required : Yes' "dependency.txt"; then
        echo "YES" > app.txt
        grep -i '.com' "dependency.txt" > rep.txt
        cat "rep.txt"
        cut -d: -f1 rep.txt | xargs > email.txt
        cut -d: -f2 rep.txt | xargs > jira.txt
        else
        echo "NO" > app.txt
        fi'''
        sh 'cat app.txt'
        sh 'cat email.txt'
        sh 'cat jira.txt'
      }
      post {
        failure {
          echo "SCM Checkout Stage Failed"
          emailext (
            subject: "Git Job Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            attachmentsPattern: '*.csv',
            body: """SCM Checkout Stage Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Git Checkout ID : ${env.GIT_COMMIT}
            Git Branch : ${env.GIT_BRANCH}
            Check console output at ${env.BUILD_URL}>${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
            to: 'Daljit_Singh@external.mckinsey.com'
            )
        }
      }
    }
    stage('Team_Check') {
      environment {
        DEV = readFile('app.txt')
        EML = readFile('email.txt')
        JIRA = readFile('jira.txt')
      }
      steps {
        script
        {
          if (DEV.trim().equals("YES"))
          {
            echo "Dependency needs to be checked by teams"
            echo "$EML"
            echo "$JIRA"
            emailext (
              subject: "Code Deployment | Input Required |'${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
              body: """Input is required please follow the given link \n
              Job Name'${env.JOB_NAME} : [${env.BUILD_NUMBER}]'\n
              Git Checkout ID : ${env.GIT_COMMIT}\n
              Git Branch : ${env.GIT_BRANCH}\n
              JIRA : ${env.JIRA}:\n
              Support EMAIL : ${env.EML}
              Check console output at ${env.RUN_DISPLAY_URL}>${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
              mimeType: 'text/html',
              to: 'Daljit_Singh@external.mckinsey.com'
              )
          }
          else
          {echo "No Dependency is Required, automation is proceeding for further Deployment Step On DEV"}
        }
      }
    }
    stage("Deployment_Dev") {
      environment {
        DEV_D = readFile('app.txt')
      }
      steps {
        script {
          if (DEV_D.trim().equals("YES"))
          {
            input message: 'Please intiate Deployment Process-DEV'
            echo "This is an IF Block"
            sh 'pwd'
            echo "Deployment over EBS DEV Environment has been started"
            sh '/tmp/scripts/it_scripts/logic_dev.sh > /tmp/logs/dev_out.log'
            sh '''if egrep -iq 'Error|ORA|Failure' "/tmp/logs/dev_out.log"; then
            echo "Error found please check below logs or from /tmp/logs/dev_out.log"
            cat /tmp/logs/dev_out.log
            exit 1
            fi'''
            sh 'cat /tmp/logs/dev_out.log'
            echo "Deployment after interective mode is done"
          }
          else
          {
            echo "This is ELSE Block"
            sh 'pwd'
            echo "Deployment over EBS DEV Environment has been started"
            sh '/tmp/scripts/it_scripts/logic_dev.sh > /tmp/logs/dev_out.log'
            sh '''if egrep -iq 'Error|ORA|Failure' "/tmp/logs/dev_out.log"; then
            echo "Error found please check below logs or from /tmp/logs/dev_out.log"
            cat /tmp/logs/dev_out.log
            exit 1
            fi'''
            sh 'cat /tmp/logs/dev_out.log'
            echo "Deployment over Dev is triggered without interaction"
          }
        }
      }
      post {
        failure {
          echo "Deployment over Dev is Failed"
          emailext (
            subject: "Dev Deployment Failed: '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            attachmentsPattern: 'deploy_rollback.csv',
            body: """Deployment Stage Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Check console output at ${env.BUILD_URL}>${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
            to: 'Daljit_Singh@external.mckinsey.com'
            )
        }
      }
    }
    stage('Unit_Test'){
      steps {
        echo "UT Test Cases Trggered"
        sh 'printenv'
        echo "Toad Testing has been started for latest commit"
        sh '''cat dependency.txt | grep pkb > pkb_test.csv
        cut -d'.' -f1 pkb_test.csv | cut -c2- > unit.csv
        cat unit.csv'''
        sh '/tmp/scripts/it_scripts/unit_test.sh > /tmp/logs/unit_test.log'
        sh '''if egrep -iq 'Error|ORA|Failure' "/tmp/logs/unit_test.log"; then
        echo "Error found please check below logs or from /tmp/logs/unit_test.log"
        cat /tmp/logs/unit_test.log
        fi'''
        sh 'echo "UT Test cases passed"'
        sh 'cat /tmp/logs/unit_test.log'
        sh 'cp /tmp/report/* .'
      }
      post {
        failure {
          echo "Unit  Test Case Failed"
          emailext (
            subject: "Job Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """Unit Testing Stage Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Check console output at ${env.BUILD_URL}>${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
            to: 'Daljit_Singh@external.mckinsey.com'
            )
        }
        always {
          echo "Report of Unit Test Cases has been Published"
          emailext (
            subject: "Report UT Test : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            attachmentsPattern: '*.html',
            body: """Unit Testing Stage Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Check console output at ${env.BUILD_URL}>${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
            to: 'Daljit_Singh@external.mckinsey.com'
            )
        }
      }
    }
    stage('FUT_DEV'){
      steps {
        echo "UT Test Cases Trggered"
        echo "Report will be Published"
      }
    }
    stage("Deploy_QA") {
      environment {
        DEV_QA = readFile('app.txt')
      }
      steps {
        script {
          if (DEV_QA.trim().equals("YES"))
          {
            input message: 'Please intiate Deployment Process-QA'
            echo "This is an IF Block"
          }
          else
          {
            echo "This is ELSE Block"
            sh 'pwd'
          }
        }
      }
    }
    stage('FUT_QA'){
      steps {
        echo "UT Test Cases Trggered"
        echo "Report will be Published"
      }
    }

    stage('Prod_Approval'){
      steps {
        echo "UT Test Cases Trggered"
        echo "Report will be Published"
      }
    }
    stage('Service Now'){
      steps {
        echo "Ticket has been created"
      }
    } 
  }
}