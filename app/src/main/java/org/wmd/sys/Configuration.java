package org.wmd.sys;

import org.wmd.BuildConfig;

import java.util.function.Function;

public class Configuration<T> {

    public static final Configuration<String> WEB_APP = new Configuration<>(BuildConfig.WEB_APP, StateInit.STRING);
    public static final Configuration<String> ONESIGNAL_APP_ID = new Configuration<>(BuildConfig.ONE_SIGNAL_APP_ID, StateInit.STRING);
    public static final Configuration<Boolean> ONESIGNAL_ENABLED = new Configuration<>(BuildConfig.ONE_SIGNAL_ENABLED, StateInit.BOOLEAN);

    private interface StateInit<T> extends Function<String, T> {
        StateInit<Boolean> BOOLEAN = Boolean::parseBoolean;

        StateInit<Integer> INT = Integer::getInteger;

        StateInit<String> STRING = String::toString;
    }

    private final String property;
    private volatile T state;

    Configuration(String property, StateInit<? extends T> init) {
        this.property = property;
        this.state = init.apply(property);
    }

    public String getProperty() {
        return property;
    }

    public void set(T value) {
        this.state = value;
    }

    public T get() {
        return state;
    }

    public T get(T defaultValue) {
        T state = this.state;
        if (state == null) {
            state = defaultValue;
        }

        return state;
    }
}
