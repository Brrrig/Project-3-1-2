package habsida.spring.boot_security.demo.services;

import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        if (user.getPassword() == null) {
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<User> userById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<UserDetails> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }
}
