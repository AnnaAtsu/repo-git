package org.example.generator;


// Для шаблона Value Object
public class UserVO {
    private final String username;
    private final String password;
    private final String role;
    private final String status;
    private final String employeeName;

    public UserVO(String username, String password, String role, String status, String employeeName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.employeeName = employeeName;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
    public String getEmployeeName() { return employeeName; }




}
