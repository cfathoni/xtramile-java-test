import { Patient } from './Patient';

export interface PatientListPayload {
  totalPages: number;
  totalData: number;
  data: Patient[];
}
