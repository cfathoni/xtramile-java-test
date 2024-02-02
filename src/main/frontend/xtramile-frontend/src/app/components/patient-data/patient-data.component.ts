import { Component, EventEmitter, Input, Output } from '@angular/core';
import {Patient, PatientAddress} from '../../models/Patient';

@Component({
  selector: 'app-patient-data',
  templateUrl: './patient-data.component.html',
  styleUrls: ['./patient-data.component.scss']
})
export class PatientDataComponent {

  @Output() edit= new EventEmitter<number>();
  @Output() delete = new EventEmitter<number>();
  @Output() search = new EventEmitter<string>();
  @Output() activePageChanged = new EventEmitter<number>();

  @Input() patients: Patient[] = [];
  @Input() pages: number[] = [];
  @Input() activePage: number = 1;
  @Input() totalData: number = 0;

  keyword: string = '';

  getAustralianAddress(address: PatientAddress) {
    return `${address.address}, ${address.suburb}, ${address.state}, ${address.postcode}`;
  }
}
