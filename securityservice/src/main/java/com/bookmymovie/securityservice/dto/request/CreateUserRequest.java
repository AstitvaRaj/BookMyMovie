package com.bookmymovie.securityservice.dto.request;

import com.bookmymovie.securityservice.dto.enums.Gender;
import com.bookmymovie.securityservice.entity.UserDetails;
import com.bookmymovie.securityservice.entity.Users;
import com.bookmymovie.securityservice.repository.UserTypeEntityRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserRequest {

    @NotBlank(message = "Password is mandatory field")
    private String password;

    @NotNull
    private Integer userTypeId;

    @NotBlank(message = "Firstname is mandatory field")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory field")
    private String lastName;

    @Email(message = "Provide valid Email id")
    @NotBlank(message = "Email is mandatory field")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNo;

    private UUID userId;

    @NotNull
    private Integer countryCode;

    private Gender gender = Gender.UNKNOWN;

    public Users generateUsers(PasswordEncoder encoder, UserTypeEntityRepository userTypeEntity){
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setUserId(userId);
        user.setUserTypeEntity(userTypeEntity.findById(userTypeId.longValue()).get());
//        user.setTypeId(Long.valueOf(userTypeId));
        UserDetails userDetails = new UserDetails();
        userDetails.setGender(gender);
        userDetails.setPhoneNo(phoneNo);
        userDetails.setCountryCode(countryCode);
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        user.setUserDetails(List.of(userDetails));
        return user;
    }
}
