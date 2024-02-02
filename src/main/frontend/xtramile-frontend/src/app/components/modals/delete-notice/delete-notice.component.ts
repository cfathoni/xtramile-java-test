import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-delete-notice',
  templateUrl: './delete-notice.component.html',
  styleUrls: ['./delete-notice.component.scss']
})
export class DeleteNoticeComponent {

  @Input() patientId: number | undefined;
  @Output() close = new EventEmitter<void>
  @Output() delete = new EventEmitter<number>
}
