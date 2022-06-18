package io.github.alperensert.capmonster;

import com.google.gson.Gson;

public class getBalanceAPI {
    private static final transient String url = "https://api.capmonster.cloud/getBalance";
    private static final transient String method = "POST";
    String clientKey;

    public getBalanceResponse getBalanceAmount(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        String response = CapMonsterClient.request(url, jsonString);
        return gson.fromJson(response, getBalanceResponse.class);
    }

    public static class getBalanceResponse{
        int errorId; //always
        String errorCode;
        double balance;

        public String toString(){
            return "errorId: " + errorId +", errorCode: " + errorCode + ", balance: " + balance;
        }
    }
}
