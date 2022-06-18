package io.github.alperensert.capmonster;

import io.github.alperensert.capmonster.task.Task;
import com.google.gson.Gson;

public class createTaskAPI {
    private static final transient String url = "https://api.capmonster.cloud/createTask";
    private static final transient String method = "POST";

    String clientKey;
    Task task;
    String callbackUrl;
    public createTaskResponse submitTask(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        String response = CapMonsterClient.request(url, jsonString);
        return gson.fromJson(response, createTaskResponse.class);
    }

    public static class createTaskResponse {
        int errorId; //always
        String errorCode;
        String errorDescription;
        int taskId; //always

        public String toString() {
            return "errorId: " + errorId + ", errorCode: " + errorCode + ", errorDescription: " + errorDescription + ", taskId: " + taskId;
        }
    }
}
