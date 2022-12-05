import { EmailService } from './../../services/email.service';
import { Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-email-details',
  templateUrl: './email-details.component.html',
  styleUrls: ['./email-details.component.scss']
})
export class EmailDetailsComponent implements OnInit {

  emailForm = this.form.group({
    toEmail: ['', Validators.required],
    subject: ['', Validators.required],
    body: ['', Validators.required]
  });

  constructor(
    private form: FormBuilder,
    private emailService: EmailService
  ) { }

  ngOnInit(): void {
  }

  sendEmail() {
    this.emailService.sendEmail(this.emailForm.value).subscribe({
      next: () => {
        alert(`E-mail enviado com sucesso para "${this.emailForm.value.toEmail}"!`);
      },
      error: () => {
        alert("O e-mail não pôde ser enviado.");
      }
    })
  }

}
