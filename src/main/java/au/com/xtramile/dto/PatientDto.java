package au.com.xtramile.dto;

import lombok.Data;

@Data
public class PatientDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private String gender;
  private PatientAddressDto australianAddress;
  private String phoneNo;
}
