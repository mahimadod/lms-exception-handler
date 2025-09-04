pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK17', type: 'jdk'
        MAVEN_HOME = tool name: 'Maven3.9.9', type: 'maven'
    }

    tools {
        maven 'Maven3.9.9'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mahimadod/lms-exception-handler.git'
            }
        }

        stage('Build & Deploy to GitHub Packages') {
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    configFileProvider([configFile(fileId: 'github-settings', variable: 'MAVEN_SETTINGS')]) {
                        withEnv([
                            "JAVA_HOME=${env.JAVA_HOME}",
                            "MAVEN_HOME=${env.MAVEN_HOME}",
                            "PATH=${env.JAVA_HOME}/bin:${env.MAVEN_HOME}/bin:$PATH"
                        ]) {
                            sh 'mvn clean deploy --settings $MAVEN_SETTINGS -DskipTests'
                        }
                    }
                }
            }
        }
    }
}
