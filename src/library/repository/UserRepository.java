package library.repository;

import library.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final Map<Integer, User> users;
    private int nextId;

    public UserRepository() {
        this.users = new HashMap<Integer, User>();
        this.nextId = 1;
    }

    public User add(String nick, String email, String password, String firstName, String lastName){
        User user = new User(nextId, nick, email, password, firstName, lastName);
        users.put(nextId, user);
        nextId++;
        return user;
    }
    public User add(String email, String password, String firstName, String lastName){
        return add(null, email, password, firstName, lastName);
    }

    public User findById(int id){
        return users.get(id);
    }
    public User findByEmail(String email){
        for(User user : users.values()){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    public User findByNick(String nick){
        for(User user : users.values()){
            if (user.getNick() != null && user.getNick().equals(nick)) {
                return user;
            }
        }
        return null;
    }
    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    public boolean removeUser(int id){
        if(!users.containsKey(id)){
            return false;
        }
        users.remove(id);
        return true;
    }
}
