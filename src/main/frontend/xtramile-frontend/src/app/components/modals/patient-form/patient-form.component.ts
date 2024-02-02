import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Patient} from "../../../models/Patient";

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent {
  @Input() patient: Patient | undefined;
  @Output() close = new EventEmitter<void>;
  @Output() save = new EventEmitter<Patient>;
  alertShown = false;
  hideAlert() {
    this.alertShown = false;
  }

  proceedSave() {
    if(this.validate()) {
      this.save.emit(this.patient);
    } else {
      this.alertShown = true;
    }
  }

  private validate() {
    return !!(this.patient && this.patient.firstName &&
      this.patient.lastName && this.patient.dateOfBirth &&
      this.patient.gender && this.patient.australianAddress.address &&
      this.patient.australianAddress.suburb && this.patient.australianAddress.state &&
      this.patient.australianAddress.postcode && this.patient.phoneNo);
  }
}
