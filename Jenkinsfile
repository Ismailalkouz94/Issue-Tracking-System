pipeline {
    agent any
    tools {
        jdk 'jdk'
        maven '3.8.4'
       
    }

    environment
    	{
    		PROJECT = "its"
    	}

    stages {
        stage("build project") {
            steps {
                echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
                sh "(cd Back-End/ ; mvn clean)"
                sh "(cd Back-End/ ; mvn install)"
            }
        }
        stage('Build Docker Image'){
            steps
            {
                sh "(cd Back-End/ ; docker build . -t ${PROJECT}:latest)"
            }
        }
        stage('Push Image to Docker Hub'){
            steps
            {
                sh "docker login -u=ismailkouz -p=55a127ca-98c5-4a5c-a024-cdb25b9d597c"
                sh 'docker tag ${PROJECT} ismailkouz/${PROJECT}:latest'
                sh 'docker push ismailkouz/${PROJECT}:latest'
            }
        }
    }


    post
    {
        always
        {
            sh "rm -rf /var/jenkins_home/workspace/${PROJECT}/*"
        }
    }
}