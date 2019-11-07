package home_accounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import home_accounting.repository.UserRepository;
import home_accounting.UserRole;
import home_accounting.entity.CustomUser;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public UserService(){}

    @Transactional
    public void deleteUsers(long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            userRepository.delete(id);
        }
    }

    @Transactional
    public boolean addUser(String login, String passHash,
                           UserRole role, String email,
                           String phone) {
        if (userRepository.existsByLogin(login))
            return false;

        CustomUser user = new CustomUser(login, passHash, role, email, phone);
        userRepository.save(user);

        return true;
    }

    @Transactional
    public void updateUser(String login, String email, String phone) {
        CustomUser user = userRepository.findByLogin(login);

        user.setEmail(email);
        user.setPhone(phone);

        userRepository.save(user);
    }
}
