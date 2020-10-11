package com.mondocto.ms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NotNull (message = "name cannot be empty")
    private String lastName;
    @NotNull(message = "first name cannot be empty")
    private String firstName;
    @Past(message = "Birth date must be in the past")
    private Date birthDate;
    private String address;
    @Email(message = "Enter a valid email address")
    @NotNull
    private String email;
    @NotNull
    @Pattern.List({
            @Pattern(regexp = "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$", message = "phone number must be valid"),
            @Pattern(regexp = "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "phone number must be valid"),
            @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "phone number must be valid"),
    })
    private String phoneNumber;
}
