package InfrlearnClass.prcatice.restapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@JsonFilter("UserInfoV2")
public class UserV2 extends User{

    private String grade;

}
