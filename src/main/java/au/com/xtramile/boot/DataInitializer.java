package au.com.xtramile.boot;

import au.com.xtramile.model.Patient;
import au.com.xtramile.model.PatientAddress;
import au.com.xtramile.service.PatientService;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

  private final PatientService patientService;

  @PostConstruct
  public void initPatientsData() {
    try {
      InputStream in = DataInitializer.class.getClassLoader().getResourceAsStream("data/patients.csv");
      if(in != null) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<Patient> patients = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
          String[] datas = line.split(",");

          PatientAddress address = new PatientAddress();
          address.setAddress(datas[4]);
          address.setSuburb(datas[5]);
          address.setState(datas[6]);
          address.setPostcode(datas[7]);

          Patient patient = new Patient();
          patient.setFirstName(datas[0]);
          patient.setLastName(datas[1]);
          patient.setDateOfBirth(datas[2]);
          patient.setGender(datas[3]);
          patient.setPhoneNo(datas[8]);
          patient.setAustralianAddress(address);

          patients.add(patient);
        }
        patientService.savePatientsInBatch(patients);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
