package capmonster;

import capmonster.task.Task;
import capmonster.createTaskAPI.createTaskResponse;
import capmonster.getBalanceAPI.getBalanceResponse;
import capmonster.getTaskResultAPI.getTaskResultResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CapMonsterClient {
    private String clientKey; //required;
    private createTaskAPI createTaskAPI;
    private getTaskResultAPI getTaskResultAPI;
    private getBalanceAPI getBalanceAPI;
    public CapMonsterClient(){
        createTaskAPI = new createTaskAPI();
        getTaskResultAPI = new getTaskResultAPI();
        getBalanceAPI = new getBalanceAPI();
    }

    public CapMonsterClient(String clientKey){
        this();
        this.clientKey = clientKey;
    }

    public int createTask(Task task){
        return createTask(task,"");
    }

    public int createTask(Task task, String callbackUrl){
        createTaskAPI.clientKey = clientKey;
        createTaskAPI.task = task;
        createTaskAPI.callbackUrl = callbackUrl;

        createTaskResponse resp = createTaskAPI.submitTask();
//        System.out.println(resp);
        return resp.taskId;
    }

    public double getBalance(){
        getBalanceAPI.clientKey = clientKey;

        getBalanceResponse resp  = getBalanceAPI.getBalanceAmount();
//        System.out.println(resp);
        return resp.balance;
    }

    public solution getResult(int taskId){
        getTaskResultAPI.clientKey = clientKey;
        getTaskResultAPI.taskId = taskId;

        getTaskResultResponse resp = getTaskResultAPI.getTaskStatus();
//        System.out.println(resp);

        return resp.solution;
    }

    public static String request(String path, String body) {
        try {
            URL url = new URL(path);
            HttpURLConnection http = null;

                http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setConnectTimeout(5000);
            http.setReadTimeout(5000);

            try(OutputStream os = http.getOutputStream()){
                byte[] input = body.getBytes();
                os.write(input,0,input.length);
            }

            int code = http.getResponseCode();
            //System.out.println(code);
            try(BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                //System.out.println(response);
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
