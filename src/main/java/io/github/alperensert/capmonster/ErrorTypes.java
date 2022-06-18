package io.github.alperensert.capmonster;

import java.util.HashMap;
import java.util.Map;

public enum ErrorTypes {
    ERROR_KEY_DOES_NOT_EXIST("ERROR_KEY_DOES_NOT_EXIST", "Account authorization key not found in the system or has incorrect format (length is not )"),
    ERROR_ZERO_CAPTCHA_FILESIZE("ERROR_ZERO_CAPTCHA_FILESIZE", "The size of the captcha you are uploading is less than 100 bytes."),
    ERROR_TOO_BIG_CAPTCHA_FILESIZE("ERROR_TOO_BIG_CAPTCHA_FILESIZE", "The size of the captcha you are uploading is more than 50,000 bytes."),
    ERROR_ZERO_BALANCE("ERROR_ZERO_BALANCE", "Account has zero balance"),
    ERROR_IP_NOT_ALLOWED("ERROR_IP_NOT_ALLOWED", "Request with current account key is not allowed from your IP"),
    ERROR_CAPTCHA_UNSOLVABLE("ERROR_CAPTCHA_UNSOLVABLE", "This type of captchas is not supported by the service or the image does not contain an answer, perhaps it is too noisy. It could also mean that the image is corrupted or was incorrectly rendered."),
    ERROR_NO_SUCH_CAPCHA_ID("ERROR_NO_SUCH_CAPCHA_ID", "The captcha that you are requesting was not found. Make sure you are requesting a status update only within 5 minutes of uploading."),
    WRONG_CAPTCHA_ID("WRONG_CAPTCHA_ID", "The captcha that you are requesting was not found. Make sure you are requesting a status update only within 5 minutes of uploading."),
    CAPTCHA_NOT_READY("CAPTCHA_NOT_READY", "The captcha has not yet been solved"),
    ERROR_IP_BANNED("ERROR_IP_BANNED", "You have exceeded the limit of requests with the wrong api key, check the correctness of your api key in the control panel and after some time, try again"),
    ERROR_NO_SUCH_METHOD("ERROR_NO_SUCH_METHOD", "This method is not supported or empty"),
    ERROR_TOO_MUCH_REQUESTS("ERROR_TOO_MUCH_REQUESTS", "You have exceeded the limit of requests to receive an answer for one task. Try to request the result of the task no more than 1 time in 2 seconds."),
    ERROR_DOMAIN_NOT_ALLOWED("ERROR_DOMAIN_NOT_ALLOWED", "Captcha from some domains cannot be solved in CapMonster Cloud. If you try to create a task for such a domain, this error will return.");
    public final String errorType;
    public final String errorMessage;


    ErrorTypes(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    private static final Map<String, ErrorTypes> BY_ERROR_TYPE = new HashMap<>();

    static {
        for (ErrorTypes e: values()) {
            BY_ERROR_TYPE.put(e.errorType, e);
        }
    }

    public static ErrorTypes valueOfLabel(String label) {
        return BY_ERROR_TYPE.get(label);
    }
}
