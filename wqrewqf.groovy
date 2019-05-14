stage('Dev') {
  environment {
    FOO = "BAR"
  }
  steps{
   script {
    if {DEV_ROCK.trim().equals("YES")}
     {
      input {
        message "Should we continue?"
        ok "Yes, we should."
        submitter "alice,bob"
        parameters {
          string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        } 
      }
      } else {
        echo 'I execute elsewhere'
      }
      steps {
        echo 'Started Dev Deployment'
      }
    }
  }
}