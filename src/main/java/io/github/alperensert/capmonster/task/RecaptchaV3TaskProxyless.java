package io.github.alperensert.capmonster.task;

public class RecaptchaV3TaskProxyless extends NoCaptchaTaskProxyLess{
    public String type = "RecaptchaV3TaskProxyless";
    public double minScore; //required
    public String pageAction;
}
