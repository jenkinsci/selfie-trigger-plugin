package org.jenkinsci.plugins.selfie;

import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;

/**
 * Schedules a new build when the previous build completes.
 *
 * @author Abhi
 */
public class SelfieRunListener extends RunListener<AbstractBuild<?,?>> {
    @Override
    public void onCompleted(AbstractBuild<?, ?> build, TaskListener listener) {
        if (build.getProject().getTrigger(SelfieTrigger.class) != null) {
            build.getProject().getTrigger(SelfieTrigger.class).scheduleBuild();
        }
    }
}
