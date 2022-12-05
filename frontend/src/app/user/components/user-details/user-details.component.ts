import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  userForm = this.form.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', Validators.required]
  });

  constructor(
    private form: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  createUser() {
    this.userService.createUser(this.userForm.value).subscribe({
      next: () => {
        alert(`Usuário criado com sucesso!\nVocê irá receber um e-mail em "${this.userForm.value.email}".`);
      },
      error: () => {
        alert("O usuário não pôde ser criado.");
      }
    })
  }

}
