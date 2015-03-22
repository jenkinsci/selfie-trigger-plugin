#Selfie Build Trigger Plugin#

This plugin enables a project to trigger itself recursively after a  delay, the delay can be configured while creating the project. If the delay is zero, then the subsequent build triggers immediately once the previous one is over.


##Installation steps##
1. git clone
2. Follow the "Setting Up Environment" section in this guide - https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial
3. mvn package - This creates a selfie.hpi file in the target folder.
4. Open plugin manager in Jenkins and navigate to the Advanced tab.
5. Upload the selfie.hpi file using the Upload Plugin section.
6. Enable the plugin.
7. While creating a new project, you should see "Selfie Build Trigger" as one of the build triggers.

