import com.cloudbees.plugins.credentials.Credentials
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.common.StandardUsernameCredentials
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import hudson.EnvVars
import hudson.model.User
import hudson.security.HudsonPrivateSecurityRealm
import hudson.security.Permission
import hudson.security.ProjectMatrixAuthorizationStrategy
import hudson.slaves.EnvironmentVariablesNodeProperty
import hudson.util.DescribableList
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.Jenkins
import jenkins.model.JenkinsLocationConfiguration

init()

private static void init() {
//    initJenkinsAdmin()
    initUsers()
    initBaseParams()
    initCredentials()
    initDslJobs()
    Jenkins.getInstanceOrNull().save()
}

private static void initBaseParams() {
    int executorsCount = 5
    Jenkins.getInstanceOrNull().setNumExecutors(executorsCount)
    initVariables()
}

private static void initVariables() {
    DescribableList globalNodeProperties = Jenkins.getInstanceOrNull().getGlobalNodeProperties()
    List<EnvironmentVariablesNodeProperty> envVarsNodePropertyList = globalNodeProperties.getAll(EnvironmentVariablesNodeProperty.class)
    EnvironmentVariablesNodeProperty newEnvVarsNodeProperty
    EnvVars envVars
    if (envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0) {
        newEnvVarsNodeProperty = new EnvironmentVariablesNodeProperty();
        globalNodeProperties.add(newEnvVarsNodeProperty)
        envVars = newEnvVarsNodeProperty.getEnvVars()
    } else {
        envVars = envVarsNodePropertyList.get(0).getEnvVars()
    }
    //TODO:deletes a local env
    envVars.put("ENVIRONMENTS", "local\ndev\nprod")
    envVars.put("DIV_DOCKER_REPOSITORY", System.getenv("DIV_DOCKER_REPOSITORY"))
    envVars.put("DIV_GIT_PATH", System.getenv("DIV_GIT_PATH"))
}

private static void initDslJobs() {
    createDslJob('/usr/share/jenkins/ref/jobs/jobs.gdsl')
}

private static void createDslJob(String path) {
    def jobDslScript = new File(path)
    def workspace = new File('.')
    def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
    new DslScriptLoader(jobManagement).runScript(jobDslScript.text)
}

private static void initUsers() {
    def instance = Jenkins.getInstanceOrNull();

    HudsonPrivateSecurityRealm hudsonRealm = new HudsonPrivateSecurityRealm(false)
    hudsonRealm.createAccount(System.getenv("JENKINS_AOLEYNIKOV_LOGIN"), System.getenv("JENKINS_AOLEYNIKOV_PASSWORD"))
    instance.setSecurityRealm(hudsonRealm)
    instance.save()

    User user = User.getOrCreateByIdOrFullName(System.getenv("JENKINS_AOLEYNIKOV_LOGIN"))

    Set<Permission> adminPermissionSet = new HashSet<Permission>();
    adminPermissionSet.add(Jenkins.ADMINISTER)
    def authStrategy = new ProjectMatrixAuthorizationStrategy()
    adminPermissionSet.each { p -> authStrategy.add(p, user.getId()) }

    instance.setAuthorizationStrategy(authStrategy)
    instance.save()
}

private static void initCredentials() {
    Credentials sshAnsibleUser = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "div_ssh_ansible_credential", "",
            System.getenv("DIV_SSH_LOGIN"),
            System.getenv("DIV_SSH_PASSWORD"))
    Credentials git = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "div_git_credential", "",
            System.getenv("DIV_GIT_LOGIN"),
            System.getenv("DIV_GIT_PASSWORD"))
    Credentials docker = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "div_docker_credential", "",
            System.getenv("DIV_DOCKER_LOGIN"),
            System.getenv("DIV_DOCKER_PASSWORD"))
    SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), git)
    SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), docker)
    SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), sshAnsibleUser)
}

private static void initJenkinsAdmin() {
    def jenkinsParameters = [
            email: 'Jenkins Admin <yurolejniko@yandex.ru>',
            url  : 'http://divide-experience.com/'
    ]

// get Jenkins location configuration
    def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()

// set Jenkins URL
    jenkinsLocationConfiguration.setUrl(jenkinsParameters.url)

// set Jenkins admin email address
    jenkinsLocationConfiguration.setAdminAddress(jenkinsParameters.email)

// save current Jenkins state to disk
    jenkinsLocationConfiguration.save()
}
