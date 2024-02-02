import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FilterAddComponent } from './components/filter-add/filter-add.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientDataComponent } from './components/patient-data/patient-data.component';
import { PatientFormComponent } from './components/modals/patient-form/patient-form.component';
import { DeleteNoticeComponent } from './components/modals/delete-notice/delete-notice.component';
import {FormsModule} from "@angular/forms";
import { AlertComponent } from './components/modals/alert/alert.component';

@NgModule({
  declarations: [
    AppComponent,
    FilterAddComponent,
    PatientDataComponent,
    PatientFormComponent,
    DeleteNoticeComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
