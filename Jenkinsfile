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
                docker.build("${PROJECT}:latest")
//                 sh "(cd Back-End/ ; docker build . -t ${PROJECT}:latest)"
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