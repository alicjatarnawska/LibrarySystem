package library.entities;

import java.util.Objects;

public class User {
    private final int id;
    private String nick;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public User(int id, String nick, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.USER;
    }
    public User(int id, String email, String password, String firstName, String lastName){
        this(id, null, email, password, firstName, lastName);
    }

    //ID
    public int getId() {
        return id;
    }

    //NICK
    public String getNick() {
        return nick;
    }
    public void changeNick(String nick){
        this.nick = nick;
    }

    //EMAIL
    public String getEmail() {
        return email;
    }
    public void changeEmail(String email){
        this.email = email;
    }

    //PASSWORD
    public void changePassword(String password){
        this.password = password;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    //FIRSTNAME
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //LASTNAME
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //ROLE
    public Role getRole() {
        return role;
    }
    public void changeRole(Role role) {
        this.role = role;
    }

    //DISPLAYNAME
    public String getDisplayName(){
        if(this.nick == null){
            return firstName + " " + lastName.charAt(0) + ".";
        }
        else {
            return nick;
        }
    }

    @Override
    public String toString(){
        return "User {" +
                "id =" + id +
                ", nick ='" + nick + "'" +
                ", email ='" + email + "'" +
                ", firstname ='" + firstName + "'" +
                ", lastname ='" + lastName + "'" +
                ", role =" + role +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
