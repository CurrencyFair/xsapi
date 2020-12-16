node {
   stage('Checkout') {
     git branch: 'master',
       credentialsId: 'fdb088af-f26a-47e8-b109-b49857442ef3',
       url:'git@github.com:CurrencyFair/xsapi'
   }
   stage('Build') {
     sh 'npm i'
     sh 'mkdir -p ./dist'
     sh 'npm run build:docs'
   }
   stage('Deploy to s3') {
     sh 'aws s3 sync ./dist s3://cf-production-xsapi-reference-origin'
   }
   stage('Cleanup Workspace') {
     cleanWs()
   }
}
