pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK17', type: 'jdk'
        MAVEN_HOME = tool name: 'Maven3.9.9', type: 'maven'
        PATH = "${tool name: 'JDK17', type: 'jdk'}/bin:${tool name: 'Maven3.9.9', type: 'maven'}/bin:${env.PATH}"
    }

    tools {
        maven 'Maven3.9.9'
        jdk 'JDK17'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                // Don't need both 'checkout scm' and 'git' — choose one
                // If you are using 'Pipeline script from SCM', use only this:
                checkout scm
            }
        }

        stage('Build & Deploy to GitHub Packages') {
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    configFileProvider([configFile(fileId: 'github-settings', variable: 'MAVEN_SETTINGS')]) {
                        sh 'mvn clean deploy --settings $MAVEN_SETTINGS -DskipTests'
                    }
                }
            }
        }
    }
}
