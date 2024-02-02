package au.com.xtramile.controller.test;

import au.com.xtramile.controller.PatientController;
import au.com.xtramile.dto.PatientDto;
import au.com.xtramile.dto.PatientListPayload;
import au.com.xtramile.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PatientControllerTest {

  @Mock
  private PatientService patientService;

  @InjectMocks
  private PatientController patientController;

  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
  }

  @Test
  public void testGetAllPatientsNoKeywords() throws Exception {
    when(patientService.getPatientById(anyLong())).thenReturn(Optional.empty());
    when(patientService.getAllPatients(anyString(), anyInt(), anyInt()))
        .thenReturn(new PatientListPayload());

    mockMvc.perform(get("/api/patients")
            .param("page", "1")
            .param("size", "10"))
        .andExpect(status().isOk());

    verify(patientService, times(0)).getPatientById(anyLong());
    verify(patientService, times(1)).getAllPatients(null, 1, 10);
  }

  @Test
  public void testGetAllPatientsById() throws Exception {
    when(patientService.getPatientById(anyLong())).thenReturn(Optional.empty());
    when(patientService.getAllPatients(anyString(), anyInt(), anyInt()))
        .thenReturn(new PatientListPayload());

    mockMvc.perform(get("/api/patients")
            .param("keyword", "010")
            .param("page", "1")
            .param("size", "10"))
        .andExpect(status().isOk());

    verify(patientService, times(1)).getPatientById(10L);
    verify(patientService, times(0)).getAllPatients(anyString(), anyInt(), anyInt());
  }

  @Test
  public void testGetAllPatientsByKeyword() throws Exception {
    when(patientService.getPatientById(anyLong())).thenReturn(Optional.empty());
    when(patientService.getAllPatients(anyString(), anyInt(), anyInt()))
        .thenReturn(new PatientListPayload());

    mockMvc.perform(get("/api/patients")
            .param("keyword", "John")
            .param("page", "1")
            .param("size", "10"))
        .andExpect(status().isOk());

    verify(patientService, times(0)).getPatientById(anyLong());
    verify(patientService, times(1)).getAllPatients("John", 1, 10);
  }

  @Test
  public void testGetPatientById() throws Exception {
    when(patientService.getPatientById(anyLong()))
        .thenReturn(Optional.of(new PatientDto()));

    mockMvc.perform(get("/api/patients/1"))
        .andExpect(status().isOk());

    verify(patientService, times(1)).getPatientById(anyLong());
  }

  @Test
  public void testSavePatient() throws Exception {
    when(patientService.savePatient(any(PatientDto.class)))
        .thenReturn(new PatientDto());

    mockMvc.perform(post("/api/patients/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new PatientDto())))
        .andExpect(status().isOk());

    verify(patientService, times(1)).savePatient(any(PatientDto.class));
  }

  @Test
  public void testDeletePatient() throws Exception {
    mockMvc.perform(delete("/api/patients/1"))
        .andExpect(status().isNoContent());

    verify(patientService, times(1)).deletePatient(anyLong());
  }
}
