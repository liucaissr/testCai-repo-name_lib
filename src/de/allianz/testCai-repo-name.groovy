
     package de.allianz

     def getLibFile(String name) {
          def lib = library identifier: 'BizDevOps_JSL@develop', retriever: modernSCM(
                  [$class: 'GitSCMSource',
                  remote: 'https://github.developer.allianz.io/JEQP/BizDevOps-JSL.git',
                  credentialsId: 'git-token-credentials'])

         if(name.equals("maven")) {
             return lib.de.allianz.bdo.pipeline.JSLMaven.new()
         }

         if(name.equals("gradle")) {
             return lib.de.allianz.bdo.pipeline.JSLGradle.new()
         }

         if(name.equals("nexus")) {
             return lib.de.allianz.bdo.pipeline.JSLNexus.new()
         }

         if(name.equals("sonarqube")) {
             return lib.de.allianz.bdo.pipeline.JSLSonarqube.new()
         }
      }

     def build() {
         getLibFile('gradle').build()
     }

      def componentTest() {
          getLibFile('gradle').testunit("component")
          
      }

      def integrationTest() {
          getLibFile('gradle').testunit("integration")
      }

      def uatTest() {
          getLibFile('gradle').testunit("uat")
      }

      def acceptanceTest() {
          getLibFile('gradle').testunit("acceptance")
      }

     def publishArtifacts() {
      //  getLibFile('nexus').push()
      }
    