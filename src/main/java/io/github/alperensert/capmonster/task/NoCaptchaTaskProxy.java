package capmonster.task;

public class NoCaptchaTaskProxy extends NoCaptchaTaskProxyLess {
    public String type = "NoCaptchaTask";
    public String proxyType; //required
    public String proxyAddress; //required
    public int proxyPort; //required
    public String proxyLogin;
    public String proxyPassword;
}
