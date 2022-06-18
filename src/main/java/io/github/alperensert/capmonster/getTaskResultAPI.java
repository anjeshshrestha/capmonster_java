package io.github.alperensert.capmonster;

import io.github.alperensert.capmonster.task.Task;
import com.google.gson.Gson;

public class getTaskResultAPI {
    private static final transient String url = "https://api.capmonster.cloud/getTaskResult";
    private static final transient String method = "POST";
    String clientKey;
    int taskId;
    public getTaskResultResponse getTaskStatus(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        String response = CapMonsterClient.request(url, jsonString);
        return gson.fromJson(response, getTaskResultResponse.class);

    }

    public static class getTaskResultResponse {
        public int errorId; //always
        public String errorCode;
        public String status; //always
        public solution solution;
        public String toString(){
            return "errorId: " + errorId +", errorCode: " + errorCode + ", status: " + status + ", solution: " + solution;
        }
    }
}
