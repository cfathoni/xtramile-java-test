package au.com.xtramile.service;

import au.com.xtramile.dto.PatientDto;
import au.com.xtramile.dto.PatientListPayload;
import au.com.xtramile.model.Patient;
import au.com.xtramile.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
  private final PatientRepository patientRepository;
  private final ModelMapper modelMapper;
  @PersistenceContext
  private EntityManager entityManager;

  public PatientListPayload getAllPatients(String name, int page, int size) {
    final Page<Patient> patientPage;
    PageRequest pageRequest = PageRequest.of(page - 1, size);
    if(name != null) {
      patientPage = patientRepository.findByName(name, pageRequest);
    } else {
      patientPage = patientRepository.findAll(pageRequest);
    }
    List<PatientDto> data = patientPage.getContent()
        .stream()
        .map(e -> modelMapper.map(e, PatientDto.class))
        .toList();

    PatientListPayload payload = new PatientListPayload();
    payload.setTotalPages(patientPage.getTotalPages());
    payload.setTotalData(patientPage.getTotalElements());
    payload.setData(data);

    return payload;
  }

  public Optional<PatientDto> getPatientById(Long id) {
    return patientRepository.findById(id)
        .map(e -> modelMapper.map(e, PatientDto.class));
  }

  public PatientDto savePatient(PatientDto patientDto) {
    Patient patient = modelMapper.map(patientDto, Patient.class);
    Patient savedPatient = patientRepository.save(patient);
    patientDto.setId(savedPatient.getId());
    return patientDto;
  }

  public void deletePatient(Long id) {
    patientRepository.deleteById(id);
  }

  @Transactional
  public void savePatientsInBatch(List<Patient> patients) {
    patients.forEach(p -> entityManager.persist(p));
    entityManager.flush();
    entityManager.clear();
  }
}
