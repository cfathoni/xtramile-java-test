package au.com.xtramile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address")
  private String address;

  @Column(name = "suburb")
  private String suburb;

  @Column(name = "state")
  private String state;

  @Column(name = "postcode")
  private String postcode;
}
