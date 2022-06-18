package io.github.alperensert.capmonster.task;

public class GeeTestTask extends GeeTestTaskProxyless{
    public String type = "GeeTestTask";
    public String proxyType; //required
    public String proxyAddress; //required
    public int proxyPort; //required
    public String proxyLogin;
    public String proxyPassword;
}
