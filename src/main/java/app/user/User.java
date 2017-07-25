package app.user;

import lombok.*;

@Value
public class User {
    public String username;
    public String email;
    public String password;
    public String create_time;

    public User(String username, String email, String password, String create_time) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.create_time = create_time;
    }
}
