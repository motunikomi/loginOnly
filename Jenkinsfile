pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'chmod +x ./gradlew'
        sh './gradlew clean assemble'
      }
    }

    stage('test') {
      parallel {
        stage('test') {
          post {
            success {
              junit resultPath
              recordIssues(tool: checkStyle(pattern: checkstyleReport))
              recordIssues(tool: pmdParser(pattern: pmdReport))
              recordIssues(tool: spotBugs(pattern: spotbugsReport))
            }

          }
          steps {
            sh './gradlew build check'
            sh './gradlew sonarqube '
          }
        }
      }
    }
    stage('deploy') {
      steps {
       sh './gradlew bootWar'
       sh 'echo ${env.BRANCH_NAME}'
      }
    }
  }
  environment {
    resultPath = 'build/test-results/**/TEST-*.xml'
    jacocoReportDir = 'build/jacoco'
    checkstyleReport = 'build/reports/checkstyle/*.xml'
    pmdReport = 'build/reports/pmd/*.xml'
    spotbugsReport = 'build/reports/spotbugs/*.xml'
  }
}