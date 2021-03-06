/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package mlicense;

/**
 * Provides an interface for security policies and implements the default
 * policy.
 *
 * @author Christian Schlichtherle
 * @version $Id$
 */
// TODO: This should be an interface which's implementation is provided by a
// factory.
public class Policy {

    /** The current policy. */
    private static Policy current;

    //
    // Static methods.
    //
    
    /**
     * Sets the current policy.
     *
     * @param current The new current policy.
     *        May be {@code null} to cause the default policy implemented by
     *        this class to be installed and returned by {@link #getCurrent()}.
     */
    public static void setCurrent(Policy current) {
        Policy.current = current;
    }

    /**
     * Returns the current policy.
     *
     * If no current policy has been set, the default policy implemented by
     * this class is installed as the current policy and returned.
     */
    public static Policy getCurrent() {
        if (current == null)
            current = new Policy();
        return current;
    }
    
    //
    // Constructors and instance methods.
    //
    
    /** Only subclasses can instantiate this class. */
    protected Policy() { }
    
    /**
     * Checks the given password for compliance to the current password policy.
     * <p>
     * The default policy implemented by this class ensures that the
     * password is at least six characters long and consists of letters and
     * digits.
     *
     * @throws IllegalPasswordException If the given password does not comply
     *         to the current policy.
     */
    public void checkPwd(String pwd) {
        final int l = pwd.length(); // may throw NullPointerException
        if (l < 6)
            throw new IllegalPasswordException();
        boolean hasLetter = false, hasDigit = false;
        for (int i = 0; i < l; i++) {
            final char c = pwd.charAt(i);
            if (Character.isLetter(c))
                hasLetter = true;
            else if (Character.isDigit(c))
                hasDigit = true;
        }
        if (!hasLetter || !hasDigit)
            throw new IllegalPasswordException();
    }
}
