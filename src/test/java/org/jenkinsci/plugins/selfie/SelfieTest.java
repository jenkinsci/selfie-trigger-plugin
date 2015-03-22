package org.jenkinsci.plugins.selfie;

import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Test cases for {@link org.jenkinsci.plugins.selfie.SelfieTrigger}
 */
public class SelfieTest {
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void test() throws IOException {
        int buildDelay = 15;

        SelfieTrigger trigger = new SelfieTrigger(buildDelay);
        FreeStyleProject project = j.createFreeStyleProject("testProject");
        project.addTrigger(trigger);
        project.getBuildersList().add(new Shell("echo Test"));
        trigger.start(project, false);

        long start = System.currentTimeMillis();

        System.out.println("Is build in queue - " + project.isInQueue());

        waitForProjectToBeInBuildQueue(project);

        waitForFirstBuild(project);

        long end = System.currentTimeMillis();

        long waitTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        System.out.println("Wait time is - " + waitTime);

        Assert.assertTrue("Once configured, project was in queue for the rest period", waitTime >= buildDelay);

        //Again wait for project to be in queue
        start = System.currentTimeMillis();

        waitForProjectToBeInBuildQueue(project);

        waitForBuildQueueExit(project);

        end = System.currentTimeMillis();

        long subsequentWaitTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        System.out.println("Subsequent wait time - " + subsequentWaitTime);

        Assert.assertTrue("Re triggered build was in queue for the rest period", subsequentWaitTime >= buildDelay);
    }

    private void waitForBuildQueueExit(FreeStyleProject project) {
        while (project.isInQueue()) {
            sleep();
        }
    }

    private void waitForFirstBuild(FreeStyleProject project) {
        while (project.getBuilds().isEmpty()) {
            sleep();
        }
    }

    private void waitForProjectToBeInBuildQueue(FreeStyleProject project) {
        while (!project.isInQueue()) {
            sleep();
        }
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
