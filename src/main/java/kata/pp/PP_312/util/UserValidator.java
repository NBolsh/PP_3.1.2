package kata.pp.PP_312.util;


import kata.pp.PP_312.dao.UserDAO;
import kata.pp.PP_312.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private UserDAO userDAO;

    @Autowired
    public UserValidator(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userDAO.findUserByEmail(user.getEmail()).isPresent()){
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
