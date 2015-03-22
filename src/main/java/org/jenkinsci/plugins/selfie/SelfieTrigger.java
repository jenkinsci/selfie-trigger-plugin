package org.jenkinsci.plugins.selfie;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Item;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * Extends Jenkins tigger to create Selfie build trigger.
 *
 * @author Abhi
 */
public class SelfieTrigger extends Trigger<AbstractProject<?, ?>> {
    @Extension
    public final static SelfieRunListener LISTENER = new SelfieRunListener();

    /**
     * Quiet period between builds.
     */
    private int delay;

    /**
     * Creates instance with the delay between successive builds.
     * @param delay
     */
    @DataBoundConstructor
    public SelfieTrigger(int delay) {
        super();
        this.delay = delay;
    }

    /**
     * Gets configured delay between builds.
     * @return
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Sets delay between successive builds.
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    private SelfieTrigger() {
    }

    @Override
    public void start(AbstractProject<?, ?> project, boolean newInstance) {
        try {
            project.addTrigger(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.start(project, newInstance);
        scheduleBuild();
    }

    /**
     * Schedules a build after the configured delay.
     */
    public void scheduleBuild() {
        job.scheduleBuild(delay, new SelfieCause("Time to wake up and build!!!"));
    }

    @Extension
    public static class DescriptorImpl extends TriggerDescriptor {

        @Override
        public boolean isApplicable(Item item) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Selfie Build Trigger";
        }
    }
}
