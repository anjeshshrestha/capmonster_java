package io.github.alperensert.capmonster.task;

public class NoCaptchaTaskProxyLess extends Task {
    public String type = "NoCaptchaTaskProxyless";
    public String websiteKey; //required
    public String recaptchaDataSValue;
    public String userAgent;
    public String cookies;
}
