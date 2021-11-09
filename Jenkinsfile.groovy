
pipeline{

	agent any

	environment {
		DOCKERHUB_CREDENTIALS=credentials('docker')
	}

	stages {

		stage('Build') {

			steps {
				sh 'docker build -t raghu0232/nodeapp:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push raghu0232/nodeapp:latest'
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}
view rawJenkinsfile hosted with ❤ by GitHub