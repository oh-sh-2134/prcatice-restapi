package InfrlearnClass.prcatice.restapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@JsonPropertyOrder(value = {"password"})
@JsonFilter("UserInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요")
    private String name;

//    @JsonIgnore
    private String password;

//    @JsonIgnore
    private String ssn;

}
