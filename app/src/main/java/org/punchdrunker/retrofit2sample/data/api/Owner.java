package org.punchdrunker.retrofit2sample.data.api;

/**
 * Created by takafumi.nanao on 9/10/15.
 */
public class Owner {
    public Owner(String name) {
        this.name = name;
    }

    public String name;

    @Override
    public String toString() {
        return name;
    }
}