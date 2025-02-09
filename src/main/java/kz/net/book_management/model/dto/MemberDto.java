package kz.net.book_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

    public String id;

    @NotBlank
    public String fullName;

    @NotBlank
    public String email;

    @NotBlank
    public String phoneNumber;

    @NotBlank
    public String address;

    public Instant createdDate;
}
