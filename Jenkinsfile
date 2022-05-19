pipeline {
  agent any
  tools {
    maven 'MAVEN_3_8_1'
    jdk 'OPEN-JDK-17'
  }
  stages {
    stage('Compile Stage') {
      steps {
        withMaven(maven: 'MAVEN_3_8_1') {
          sh 'mvn clean compile'
        }
      }
    }

    stage('Testing Stage') {
      steps {
        withMaven(maven: 'MAVEN_3_8_1') {
          sh 'mvn test'
        }
      }
    }

    stage('Package Stage') {
      steps {
        withMaven(maven: 'MAVEN_3_8_1') {
          sh 'mvn package'
        }
      }
    }
  }
}