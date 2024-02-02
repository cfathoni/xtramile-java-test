import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';
import {PatientListPayload} from "../models/PatientListPayload";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private _baseApiUrl = '/api/patients';

  constructor(private http: HttpClient) {}
  async getPatients(keyword: string, page: number, size: number): Promise<PatientListPayload> {
    let url = `${this._baseApiUrl}?page=${page}&size=${size}`;
    if(keyword) {
      url = `${this._baseApiUrl}?keyword=${keyword}&page=${page}&size=${size}`
    }
    return lastValueFrom(this.http.get<PatientListPayload>(url));
  }

  async getPatientById(id: number): Promise<any> {
    return lastValueFrom(this.http.get<any>(`${this._baseApiUrl}/${id}`));
  }

  async savePatient(patient: any): Promise<any> {
    return lastValueFrom(this.http.post<any>(`${this._baseApiUrl}/save`, patient));
  }

  async deletePatient(id: number): Promise<any> {
    return lastValueFrom(this.http.delete<any>(`${this._baseApiUrl}/${id}`));
  }
}
