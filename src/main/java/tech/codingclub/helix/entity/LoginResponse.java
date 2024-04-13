package tech.codingclub.helix.entity;

public class LoginResponse  {


    public Long id;
    public Boolean is_login;
    public String message;

    public LoginResponse(){

    }
    public LoginResponse(Long id, Boolean is_login, String message) {
        this.id = id;
        this.is_login = is_login;
        this.message = message;
    }
}
