package library.service;

import library.entities.User;
import library.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //REJESTRACJA
    public User register(String nick, String email, String password, String firstName, String lastName) {
        validateNotBlank(firstName, "Imie");
        validateNotBlank(lastName, "Nazwisko");
        validateEmail(email);
        validateEmailUnique(email);
        validatePassword(password);

        return userRepository.add(nick, email, password, firstName, lastName);
    }
    public User register(String email, String password, String firstName, String lastName){
        return register(null, email, password, firstName, lastName);
    }

    //WALIDACJA
    private void validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + ": pole nie może być puste");
        }
    }
    private void validatePassword(String password){
        validateNotBlank(password, "password");
        if(password.length() < 6){
            throw new IllegalArgumentException("Hasło musi mieć conajmniej 6 znaków");
        }
    }
    private void validateEmail(String email){
        validateNotBlank(email, "email");
        if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Niepoprawny format maila");
        }
    }
    private void validateEmailUnique(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new IllegalStateException("Podany email jest już zarejestrowany");
        }
    }
}
