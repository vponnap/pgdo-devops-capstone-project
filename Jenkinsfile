node {
	def application = "b-safe-cicd-pipeline"
	def dockerhubaccountid = "vponnap"
	def server
    def rtGradle = Artifactory.newGradleBuild()
    def buildInfo = Artifactory.newBuildInfo()
	stage('Clone repository') {
		git url: 'https://github.com/vponnap/pgdo-devops-capstone-project.git'
	}
   stage ('Artifactory configuration') {
           // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
           server = Artifactory.server 3.109.114.243
           rtGradle.tool = gradle-6.8 // Tool name from Jenkins configuration
           rtGradle.deployer repo: ARTIFACTORY_LOCAL_RELEASE_REPO, server: server
           rtGradle.resolver repo: ARTIFACTORY_VIRTUAL_RELEASE_REPO, server: server
       }
   stage('Gradle build and Test') {
           buildInfo = rtGradle.run rootDir: "/Users/Shared/git/pgdo-devops-capstone-project/",
           buildFile: '/Users/Shared/git/pgdo-devops-capstone-project/build.gradle',
           tasks: 'clean artifactoryPublish',
           buildInfo: buildInfo
       }
       stage ('Publish build info') {
                   server.publishBuildInfo buildInfo
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
