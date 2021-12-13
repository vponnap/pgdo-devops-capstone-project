node {
	def application = "b-safe-cicd-pipeline"
	def dockerhubaccountid = "vponnap"
	stage('Clone repository') {
		checkout scm
	}

    stage('Build') {
    		echo "Building the application"
    		sh "clean build"
    	}
     stage('Test') {
        		echo "Testing the application"
        	}
	stage('Build image') {
		app = docker.build("${dockerhubaccountid}/${application}:${BUILD_NUMBER}")
	}

	stage('Push image') {
		withDockerRegistry([ credentialsId: "dockerHub_Id", url: "" ]) {
		app.push()
	}
	}

	stage('Deploy') {
		sh ("docker run -d -p 9095:9080 -v /var/log/:/var/log/ ${dockerhubaccountid}/${application}:${BUILD_NUMBER}")
	}

	stage('Remove image') {
		// remove docker pld images
		sh("docker rmi ${dockerhubaccountid}/${application}:${BUILD_NUMBER} -f")
   }
}
