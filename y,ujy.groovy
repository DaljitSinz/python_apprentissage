pipeline {
  agent any
  stages {
      stage('GIT') {
      steps {
        git credentialsId: '49412063-b10f-455c-bffa-fd9c63e3e925', url: 'http://githuben.intranet.mckinsey.com/ebs-project/ebs-fin'
        sh 'echo hello'
        sh 'touch trry.txt app.txt'
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
        else
        echo "NO" > app.txt
        fi'''
        sh 'cat app.txt'
      }
    }
    stage('Dev') {
      environment {
        FOO = "BAR"
        ROCK = readFile('app.txt')
      }
      script{ 
          def returnValue = input message: 'Need some input', parameters: [string(defaultValue: '', description: '', name: 'Give me a value')]
          if (ROCK.trim().equals("YES"))
        {
          echo "$returnValue"
        steps{
          echo ("not updated during")
        }
      }
    }
  }
}
}
