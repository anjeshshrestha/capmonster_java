package io.github.alperensert.capmonster_java.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;

import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class for tasks
 * @since 1.2
 */
public abstract class Client {
    /**
     * Default time for JoinTaskResult method
     * @see #joinTaskResult(int, int)
     */
    private static final int maximumTime = 120;
    /**
     * Balance url address for api
     * @see #getBalance()
     */
    private static final String balanceUrl = "/getBalance";
    /**
     * Task result url address for api
     * @see #getTaskResult(int)
     */
    private static final String taskResultUrl = "/getTaskResult";
    /**
     * Task create url address for api
     */
    private static final String createTaskUrl = "/createTask";
    /**
     * Base host url for api
     */
    private static final String hostUrl = "https://api.capmonster.cloud";
    /**
     * Unique key of your account
     */
    protected final String CLIENT_KEY;

    /**
     * @param apiKey Your unique key for solving captchas
     */
    public Client(String apiKey) { CLIENT_KEY = apiKey; }

    /**
     * Get account's balance
     * @return account balance
     * @since 1.2
     */
    public String getBalance() {
        JsonObject data = new JsonObject();
        data.addProperty("clientKey", CLIENT_KEY);
        return makeRequest("getBalance", data).get("balance").toString();
    }

    /**
     * Get task result
     * @see #joinTaskResult(int) 
     * @param taskId ID which was obtained in createTask method
     * @return Task's result if is ready
     * @since 1.2
     */
    public JsonObject getTaskResult(int taskId) {
        JsonObject data = new JsonObject();
        data.addProperty("clientKey", CLIENT_KEY);
        data.addProperty("taskId", taskId);
        JsonObject result = makeRequest("getTaskResult", data);
        boolean isReady = isReady(result);
        return isReady ? (JsonObject) result.get("solution") : null;
    }

    /**
     * Check the task until successfully solve
     * @param taskId ID which was obtained in createTask method
     * @return Task's result when is ready
     * @throws CapmonsterException Maximum time is exceed
     * @throws InterruptedException .
     * @since 1.2
     */
    public JsonObject joinTaskResult(int taskId) throws InterruptedException {
        for (int i = 0; i <= maximumTime + 1; i += 2) {
            JsonObject result = getTaskResult(taskId);
            if (result != null) return result;
            else TimeUnit.SECONDS.sleep(2);
        }
        throw new CapmonsterException("ERROR_MAXIMUM_TIME_EXCEED", "Maximum time is exceed.");
    }

    /**
     * Check the task until successfully solve
     * @param taskId ID which was obtained in createTask method
     * @param maximumTime How long will check
     * @return Task's result when is ready
     * @throws CapmonsterException Maximum time is exceed
     * @throws InterruptedException .
     * @since 1.2
     */
    public JsonObject joinTaskResult(int taskId, int maximumTime) throws InterruptedException {
        for (int i = 0; i <= maximumTime + 1; i += 2) {
            JsonObject result = getTaskResult(taskId);
            if (result != null) return result;
            else TimeUnit.SECONDS.sleep(2);
        }
        throw new CapmonsterException("ERROR_MAXIMUM_TIME_EXCEED", "Maximum time is exceed.");
    }

    /**
     * Check response has an error message or not
     * @param response Response from api
     * @since 1.2
     */
    private static void checkResponse(String response) {
        JsonObject responseJson = JsonParser.parseString(response).getAsJsonObject();
        if (responseJson.get("errorId") != null && responseJson.get("errorId").getAsInt() == 0) {
            return;
        }
        if (responseJson.get("errorId").getAsInt() != 0) {
            throw new CapmonsterException(responseJson.get("errorCode").toString(), responseJson.get("errorDescription").toString());
        } else {
            throw new CapmonsterException("[ERROR CODE: HTTP_ERROR]", "Sometimes can be happen if capmonster servers there is too much intensity");
        }
    }

    /**
     * Check task is ready or not
     * @param response Response from api
     * @return ready or not
     * @since 1.2
     */
    private static boolean isReady(JsonObject response) {
        String status = response.get("status").toString();

        if (Objects.equals(status, "processing")) {
            return false;
        } else if (Objects.equals(status, "ready")) {
            return true;
        } else {
            throw new CapmonsterException(response.get("errorCode").toString(), response.get("errorDescription").toString());
        }
    }

    /**
     * Kinda request helper
     * @param method Request's method like getBalance or createTask
     * @param data Request data
     * @return Response from api
     * @since 1.2
     */
    protected JsonObject makeRequest(String method, JsonObject data) {
        JsonObject response = null;
        if (Objects.equals(method, "getBalance")) {
            method = balanceUrl;
        } else if (Objects.equals(method, "getTaskResult")) {
            method = taskResultUrl;
        } else if (Objects.equals(method, "createTask")) {
            method = createTaskUrl;
            // data.put("softId", 51);
            //TODO: get softid
        }
        try {
            response = requestHandler(hostUrl + method, data);
            checkResponse(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Handle with requests
     * @param url Request URL
     * @param data Request data
     * @return Response from api
     * @throws IOException #
     * @since 1.2
     */
    private static JsonObject requestHandler(String url, JsonObject data) throws IOException {
        URL parsedUrl = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection)parsedUrl.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return JsonParser.parseString(response.toString()).getAsJsonObject();
        }
    }
}
