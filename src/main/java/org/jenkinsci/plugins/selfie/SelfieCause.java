package org.jenkinsci.plugins.selfie;

import hudson.model.Cause;

/**
 * Clause cause for Selfie Trigger.
 *
 * @author Abhi
 */
public class SelfieCause extends Cause {
    private final String description;

    /**
     * Creates an instance of {@link org.jenkinsci.plugins.selfie.SelfieCause}
     * @param description
     */
    public SelfieCause(String description) {
        this.description = description;
    }

    /**
     * Returns description of the cause.
     * @return
     */
    @Override
    public String getShortDescription() {
        return this.description;
    }
}
