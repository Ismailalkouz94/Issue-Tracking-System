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
    }

    post
    	{
    		always
    		{
    			sh "sudo rm -rf /var/jenkins_home/workspace/${PROJECT}/*"
      		}
    	}
}