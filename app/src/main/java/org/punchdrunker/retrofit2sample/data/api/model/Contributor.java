package org.punchdrunker.retrofit2sample.data.api.model;

/**
 * Created by takafumi.nanao on 9/10/15.
 */
public class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}