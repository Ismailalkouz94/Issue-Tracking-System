pipeline {
    agent any
    tools {
        jdk 'jdk'
        maven '3.8.4'
       
    }

    environment
    	{
    		PROJECT = "its"
    		CONTAINER_REPOSITORY = "714089092330.dkr.ecr.us-east-1.amazonaws.com/${PROJECT}"
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
                sh "(cd Back-End/ ; docker build . -t ${CONTAINER_REPOSITORY}:latest)"
            }
        }
        stage('Push Image to AWS ECR'){
            steps
            {
                sh "docker tag ${CONTAINER_REPOSITORY}:latest  ${CONTAINER_REPOSITORY}:latest"
//                 sh "docker push ${CONTAINER_REPOSITORY}:latest"
                docker.withRegistry('https://714089092330.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:its')
                 {
                    docker.image('${CONTAINER_REPOSITORY}').push('latest')
                 }
            }
        }


//         stage('Up Docker Compose'){
//             steps
//             {
//                 sh "(cd Back-End/ ; docker-compose up --detach)"
//             }
//         }
//         stage('Push Image to Docker Hub'){
//             steps
//             {
//                 sh "docker login -u=ismailkouz -p=55a127ca-98c5-4a5c-a024-cdb25b9d597c"
//                 sh 'docker tag ${PROJECT} ismailkouz/${PROJECT}:latest'
//                 sh 'docker push ismailkouz/${PROJECT}:latest'
//             }
//         }
//         stage('Deploy to Kubernetes Cluster'){
//             steps
//             {
//                 sh "(cd Back-End/ ; kubectl apply -f k8s-app-deployment.yaml)"
//             }
//         }

    }


    post
    {
        always
        {
            sh "rm -rf /var/jenkins_home/workspace/${PROJECT}/*"
        }
    }
}