package endgame.security;

import com.ning.http.client.*;

public class Authenticator {

    public static String token;

    public static boolean validate(String username, String password){

        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        Request req = new RequestBuilder("POST")
                .setUrl("http://localhost:8080/api/login/")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addParameter("email", username)
                .addParameter("password", password)
                .build();

        int respLength = 0;
        String response = null;
        ListenableFuture<Response> future;

        try {

            response = asyncHttpClient.prepareRequest(req).execute().get().getResponseBody();
            respLength = response.length();

            token = response.split(" ")[1];

        } catch (Exception e) {

            e.printStackTrace();

        }

        return (respLength > 14 && respLength < 200); // Got a token?

    }
}