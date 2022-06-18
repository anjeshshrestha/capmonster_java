package capmonster.task;

public class FunCaptchaTask extends FunCaptchaTaskProxyless{
    public String type = "FunCaptchaTask";
    public String proxyType; //required
    public String proxyAddress; //required
    public int proxyPort; //required
    public String proxyLogin;
    public String proxyPassword;
    public String userAgent; //required
    public String cookies;
}
