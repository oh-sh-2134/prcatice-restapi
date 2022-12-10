package InfrlearnClass.prcatice.restapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFountException extends RuntimeException{
    public UserNotFountException(String m) {
        super(m);
    }
}
