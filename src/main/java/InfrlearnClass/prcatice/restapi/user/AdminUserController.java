package InfrlearnClass.prcatice.restapi.user;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;

    @PostMapping("/v1/user")
    public ResponseEntity<User> saveUserV1(@Valid @RequestBody User user) {
        User saveUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/v1/users")
    public MappingJacksonValue findAllUserV1() {
        List<User> users = userService.findAll();

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

//    @GetMapping("/v1/user/{id}")
    //http://localhost:8080/admin/user/1/?version=1
//    @GetMapping(value = "/user/{id}/",params = "version=1")
    //http://localhost:8080/admin/user/1 + Header Key : X-API-VERSION, Value : 1
    @GetMapping(value = "/user/{id}/",headers = "X-API-VERSION = 1")
    public MappingJacksonValue findUserV1(@PathVariable Long id) {
        User user = userService.findUser(id).orElseThrow(()-> new UserNotFountException(String.format("ID[%s] not found",id)));

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    // v2

//    @GetMapping("/v2/user/{id}")
    //http://localhost:8080/admin/user/1/?version=2
//    @GetMapping(value = "/user/{id}/",params = "version=2")
    //http://localhost:8080/admin/user/1 + Header Key : X-API-VERSION, Value : 2
    @GetMapping(value = "/user/{id}/",headers = "X-API-VERSION = 2")

    public MappingJacksonValue findUserV2(@PathVariable Long id) {
        User user = userService.findUser(id).orElseThrow(()-> new UserNotFountException(String.format("ID[%s] not found",id)));

        UserV2 userV2 = new UserV2();

        //Spring FrameWork 제공 : 두 인스턴스간에 공통적인적을 copy해줌
        //User -> UserV2
        BeanUtils.copyProperties(user,userV2);
        userV2.setGrade("VIP");
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "ssn", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userV2);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }



}
