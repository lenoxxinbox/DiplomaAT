package api_service;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import utils.ConfigReader;

@Getter
@Setter
public class AuthorizationRequest {
    private String password;
    private String username;

    public AuthorizationRequest(String password, String username) {
        this.password = ConfigReader.get("password");
        this.username = ConfigReader.get("username");;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
