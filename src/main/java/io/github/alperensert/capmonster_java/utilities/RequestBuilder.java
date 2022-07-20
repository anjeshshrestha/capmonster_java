package io.github.alperensert.capmonster_java.utilities;

import com.google.gson.JsonObject;

/**
 * Request builder is helps to build JSON data for tasks
 * @since 1.2
 */
public class RequestBuilder {
    /**
     * "task" data holder
     */
    private final JsonObject TASK = new JsonObject();
    /**
     * Main data holder
     */
    private final JsonObject DATA = new JsonObject();

    /**
     * Create a request from scratch
     * @param clientKey Api key
     * @param taskType Task type
     * @since 1.2
     */
    public RequestBuilder(String clientKey, String taskType) {
        DATA.addProperty("clientKey", clientKey);
        TASK.addProperty("type", taskType);
    }

    /**
     * Build the request data
     * @return JSON data
     * @since 1.2
     */
    public JsonObject build() {
        DATA.add("task", TASK);
        return DATA;
    }

    public RequestBuilder addTask(String key, String value) {
        if (value != null) TASK.addProperty(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, int value) {
        TASK.addProperty(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, boolean value) {
        TASK.addProperty(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, double value) {
        TASK.addProperty(key, value);
        return this;
    }

    public RequestBuilder addData(String key, String value) {
        if (value != null) DATA.addProperty(key, value);
        return this;
    }

    public RequestBuilder addData(String key, int value) {
        DATA.addProperty(key, value);
        return this;
    }

    public RequestBuilder addData(String key, boolean value) {
        DATA.addProperty(key, value);
        return this;
    }

    public RequestBuilder addData(String key, double value) {
        DATA.addProperty(key, value);
        return this;
    }

    /**
     * Add cookies automatically. If the cookies aren't defined, this method does nothing like expected
     * @param cookies Cookies
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addCookies(Cookies cookies) {
        if (cookies != null) TASK.addProperty("cookies", cookies.get());
        return this;
    }

    /**
     * Add no cache property to task
     * @param cache -
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addNoCache(boolean cache) {
        if (cache) TASK.addProperty("nocache", true);
        return this;
    }

    /**
     * Add user agent property to task
     * @param ua UserAgent
     * @see UserAgent
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addUserAgent(UserAgent ua) {
        if (ua != null) TASK.addProperty("userAgent", ua.get());
        return this;
    }

    /**
     * Add proxy credentials to task
     * @param proxy Proxy
     * @see Proxy
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addProxy(Proxy proxy) {
        if (proxy == null) {
            TASK.addProperty("type", TASK.get("type").getAsString()+ "Proxyless");
            return this;
        }
        if (proxy.type != null && proxy.address != null && proxy.port != 0) {
            TASK.addProperty("proxyType", proxy.type);
            TASK.addProperty("proxyAddress", proxy.address);
            TASK.addProperty("proxyPort", proxy.port);

            if (proxy.username != null && proxy.password != null) {
                TASK.addProperty("proxyLogin", proxy.username);
                TASK.addProperty("proxyPassword", proxy.password);
            }
        }
        return this;
    }
}
