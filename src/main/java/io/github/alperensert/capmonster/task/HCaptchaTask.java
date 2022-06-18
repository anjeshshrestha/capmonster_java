package capmonster.task;

public class HCaptchaTask extends HCaptchaTaskProxyless{
    public String type = "HCaptchaTask";
    public String proxyType; //required
    public String proxyAddress; //required
    public int proxyPort; //required
    public String proxyLogin;
    public String proxyPassword;
}
