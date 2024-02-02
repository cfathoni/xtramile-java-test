import { Component, OnInit } from '@angular/core';
import { PatientService } from './service/patient.service';
import { Patient, PatientAddress } from './models/Patient';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'xtramile-frontend';
  deleteModalShown = false;
  patientFormModalShown = false;
  currentPatientId: number = 0;
  patients: Patient[] = [];
  patient: Patient | undefined;
  private _pageSize = 10;
  page = 1;
  pages: number[] = [];
  totalData: number = 0;
  keyword: string = '';

  constructor(private service: PatientService) {}

  ngOnInit() {
    this.retrievePatientsData(1);
  }

  retrievePatientsData(page: number) {
    this.page = page;
    this.service.getPatients(this.keyword, this.page, this._pageSize).then(res => {
      this.patients = res.data;
      this.totalData = res.totalData;
      this.generatePages(res.totalPages);
    });
  }

  showPatientFormModal(id: number) {
    if(id == 0) {
      this.patient = this.getInitialPatient();
      this.patientFormModalShown = true;
    } else {
      this.service.getPatientById(id).then(patient => {
        this.patient = patient;
        this.patientFormModalShown = true;
      });
    }
  }

  hidePatientFormModal() {
    this.patientFormModalShown = false;
  }

  showDeleteModal(id: number) {
    this.currentPatientId = id;
    this.deleteModalShown = true;
  }

  hideDeleteModal() {
    this.currentPatientId = 0;
    this.deleteModalShown = false;
  }

  deletePatient(id: number) {
    this.hideDeleteModal();
    this.service.deletePatient(id).then(() => {
      this.page = 1;
      this.retrievePatientsData(this.page);
    });
  }

  private generatePages(totalPages: number) {
    this.pages = [];
    for(let i = 0; i < totalPages; ++i) {
      this.pages.push(i+1);
    }
  }

  savePatient(patient: Patient) {
    this.service.savePatient(patient).then(() => {
      this.retrievePatientsData(1);
      this.hidePatientFormModal();
    })
  }

  search(keyword: string) {
    this.keyword = keyword;
    this.retrievePatientsData(1);
  }

  private getInitialPatient(): Patient {
    const australianAddress: PatientAddress = { address: '', suburb: '', state: '', postcode: '' };
    return {
      id: 0,
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      gender: '',
      australianAddress,
      phoneNo: ''
    };
  }
}
