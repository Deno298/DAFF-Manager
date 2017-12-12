package dk.KeaExam.service;



import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Finds a user
     * @param username of user wished to be found
     * @return Return an authorized user
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserPrincipal(user);
    }

    /**
     * Registers user
     * @param user User to be registered
     * @return Saves the user
     */
    public User registerUser(User user){
        User finalUser = new User();
        finalUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        finalUser.setUsername(user.getUsername());
        finalUser.setEmail(user.getEmail());
        return userRepository.save(finalUser);
    }


}