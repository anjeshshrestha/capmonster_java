package io.github.alperensert.capmonster;

public class solution {
    String text; //imageToText
    String gRecaptchaResponse; //recaptcha v2/v3 Hcaptcha
    String token; //funcaptcha

    String challenge; //geetesttask
    String validate; //geetesttask
    String seccode; //geetesttask
    public String toString() {
        return "text: " + text + ", gRecaptchaResponse: " + gRecaptchaResponse + ", token: " + token
                + ", challenge: " + challenge + ", validate: " + validate + ", seccode: " + seccode;
    }

}
