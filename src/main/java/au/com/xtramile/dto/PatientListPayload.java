package au.com.xtramile.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListPayload {
  private int totalPages = 0;
  private long totalData = 0;
  private List<PatientDto> data = new ArrayList<>();
}
