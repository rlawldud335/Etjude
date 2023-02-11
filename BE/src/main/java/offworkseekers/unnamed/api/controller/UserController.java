package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.User;
import offworkseekers.unnamed.db.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/api/v1/user/login")
    public ResponseEntity UserSignUp(@RequestBody @Valid User user) {
        String userId = user.getUserId();
        User signedUser = userRepository.findById(userId).orElse(null);

        if (signedUser == null) {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
