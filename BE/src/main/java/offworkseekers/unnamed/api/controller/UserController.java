package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.User;
import offworkseekers.unnamed.db.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/api/v1/user/signup")
    public ResponseEntity UserSignUp(User user) {
        String userId = user.getUserId();
        User signedUser = userRepository.findById(userId).orElse(null);

        if (signedUser == null) {
            userRepository.save(signedUser);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
