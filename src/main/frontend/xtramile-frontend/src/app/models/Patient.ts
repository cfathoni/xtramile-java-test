export interface Patient {
  id: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  gender: string;
  australianAddress: PatientAddress;
  phoneNo: string;
}

export interface PatientAddress {
  address: string;
  suburb: string;
  state: string;
  postcode: string;
}
