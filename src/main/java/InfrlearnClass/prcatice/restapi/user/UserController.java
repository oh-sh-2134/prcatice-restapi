package InfrlearnClass.prcatice.restapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        userService.saveUser(user);
        return userService.findUser(id).orElseThrow(()-> new UserNotFountException(String.format("ID[%s] not found",id)));
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User saveUser = userService.saveUser(user);

        //HTTP Header에 생성된 User의 id를 반환함으로써 client에서 생성된 userId를 재요청할 필요가 없기 때문에 Network 부하를 줄일 수 있다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> findAllUser() {
        return userService.findAll();
    }

}
