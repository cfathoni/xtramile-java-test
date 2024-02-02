package au.com.xtramile.dto;

import lombok.Data;

@Data
public class PatientAddressDto {
  private String address;
  private String suburb;
  private String state;
  private String postcode;
}
