package org.jenkinsci.plugins.selfie;

import hudson.Extension;
import hudson.model.Describable;
import hudson.model.Descriptor;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Holds configuration parameters for Selfie Trigger.
 *
 * @author Abhi
 */
public class SelfieConfig implements Describable<SelfieConfig> {
    private int delay;

    @Override
    public Descriptor<SelfieConfig> getDescriptor() {
        return getClassDescriptor();
    }

    public static DescriptorImpl getClassDescriptor() {
        return (DescriptorImpl)Jenkins.getInstance().getDescriptorOrDie(SelfieConfig.class);
    }

    @DataBoundConstructor
    public SelfieConfig(int delay) {
        this.delay = delay;
    }

    public SelfieConfig() {
        this.delay = delay;
    }

    @Extension
    public static final class DescriptorImpl extends Descriptor<SelfieConfig> {
        @Override
        //Not used
        public String getDisplayName() {
            return "";
        }
    }
}
