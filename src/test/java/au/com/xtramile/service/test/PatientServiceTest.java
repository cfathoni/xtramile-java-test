package au.com.xtramile.service.test;

import au.com.xtramile.dto.PatientDto;
import au.com.xtramile.dto.PatientListPayload;
import au.com.xtramile.model.Patient;
import au.com.xtramile.repository.PatientRepository;
import au.com.xtramile.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

  @Mock
  private PatientRepository patientRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private PatientService patientService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllPatients() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Patient> mockPatientPage = new PageImpl<>(Collections.emptyList());
    when(patientRepository.findAll(pageRequest)).thenReturn(mockPatientPage);

    PatientListPayload result = patientService.getAllPatients(null, 1, 10);

    assertEquals(mockPatientPage.getTotalPages(), result.getTotalPages());
    assertEquals(mockPatientPage.getTotalElements(), result.getTotalData());
    assertEquals(Collections.emptyList(), result.getData());
  }

  @Test
  public void testGetPatientById() {
    Long patientId = 1L;
    Patient mockPatient = new Patient();
    PatientDto mockPatientDto = new PatientDto();
    when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));
    when(modelMapper.map(mockPatient, PatientDto.class)).thenReturn(mockPatientDto);

    Optional<PatientDto> result = patientService.getPatientById(patientId);
    assertEquals(Optional.of(mockPatientDto), result);
  }

  @Test
  public void testSavePatient() {
    PatientDto mockPatientDto = new PatientDto();
    Patient mockPatient = new Patient();
    when(modelMapper.map(mockPatientDto, Patient.class)).thenReturn(mockPatient);
    when(patientRepository.save(mockPatient)).thenReturn(mockPatient);

    PatientDto result = patientService.savePatient(mockPatientDto);

    assertEquals(mockPatientDto, result);
    verify(modelMapper, times(1)).map(mockPatientDto, Patient.class);
    verify(patientRepository, times(1)).save(mockPatient);
  }

  @Test
  public void testDeletePatient() {
    Long patientId = 1L;
    patientService.deletePatient(patientId);
    verify(patientRepository, times(1)).deleteById(patientId);
  }
}
