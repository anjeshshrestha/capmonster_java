package capmonster;

import capmonster.task.NoCaptchaTaskProxyLess;
import com.formdev.flatlaf.FlatDarkLaf;
import eu.darkbot.captcha.CaptchaSolver;
import java.net.URL;

public class maintest {
    public static void main(String[] args){
        try {
            URL url = new URL("https://darkorbit.com/");
//            String frontPage = IOUtils.read(Http.create(url.toString()).getInputStream());
            CaptchaSolver temp = new CaptchaSolver();
            CaptchaSolver.url = url.toString();
//            TwoCaptcha solver = new TwoCaptcha();
//            ReCaptcha captcha = new ReCaptcha();
//            solver.setApiKey("472422416327eb9904a58c76e462ed2c");
//            System.out.println(solver.getResult("70741716661"));
            String answer = temp.getCaptchaResponse("6LfkgUIUAAAAAETf-SZEx_exK2SEPirE8i2RZQ_U");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        CapMonsterClient test = new CapMonsterClient("6d52cf1e80b57c2b91221874f3d6c773");
//        System.out.println(test.getBalance());
//
//        NoCaptchaTaskProxyLess recaptcha = new NoCaptchaTaskProxyLess();
//        recaptcha.websiteURL = "abc";
//        recaptcha.websiteKey = "abc";
//        recaptcha.userAgent = "bca";
//        System.out.println(test.createTask(recaptcha));

    }
}
