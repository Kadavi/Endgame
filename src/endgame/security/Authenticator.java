package endgame.security;

import com.ning.http.client.*;
import endgame.model.User;

public class Authenticator {

    public static String token;

    public static User validate(String username, String password){

        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        Request req = new RequestBuilder("POST")
                .setUrl("https://www.politestare.com/api/login/")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addParameter("email", username)
                .addParameter("password", password)
                .build();

        int respLength = 0;
        String response = null;

        try {

            response = asyncHttpClient.prepareRequest(req).execute().get().getResponseBody();
            respLength = response.length();

            token = response.split(" ")[1];

        } catch (Exception e) {

            e.printStackTrace();

        }

        if (respLength > 14 && respLength < 200) {
            return new User(token);
        }
        
        return null;
    }
}