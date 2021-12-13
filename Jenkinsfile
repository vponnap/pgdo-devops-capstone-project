node {
	def application = "b-safe-cicd-pipeline"
	def dockerhubaccountid = "vponnap"
	def rtGradle = Artifactory.newGradleBuild()
    def buildInfo
	stage('Clone repository') {
		checkout scm
	}

   stage('Gradle build and Test') {
           buildInfo = rtGradle.run rootDir: "/Users/Shared/git/pgdo-devops-capstone-project/",
           buildFile: '/Users/Shared/git/pgdo-devops-capstone-project/build.gradle',
           tasks: 'clean test build'
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
