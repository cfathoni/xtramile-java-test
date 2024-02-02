package au.com.xtramile.controller;

import au.com.xtramile.dto.PatientDto;
import au.com.xtramile.dto.PatientListPayload;
import au.com.xtramile.service.PatientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @GetMapping
  public ResponseEntity<PatientListPayload> getAllPatients(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam("page") int page, @RequestParam("size") int size) {
    PatientListPayload payload = new PatientListPayload();
    if(keyword != null) {
      long id;
      try {
        id = Long.parseLong(keyword);
      } catch (Exception e) {
        id = -1;
      }

      if(id != -1) {
        PatientDto dto = patientService.getPatientById(id).orElse(null);
        if(dto != null) {
          payload.setData(List.of(dto));
          payload.setTotalPages(1);
          payload.setTotalData(1);
        }
      } else {
        payload = patientService.getAllPatients(keyword, page, size);
      }
    } else {
      payload = patientService.getAllPatients(null, page, size);
    }

    return ResponseEntity.ok(payload);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/save")
  public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto) {
    PatientDto savedPatient = patientService.savePatient(patientDto);
    return ResponseEntity.ok(savedPatient);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
    patientService.deletePatient(id);
    return ResponseEntity.noContent().build();
  }
}
