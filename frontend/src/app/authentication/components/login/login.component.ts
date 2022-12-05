import { AuthenticationService } from './../../services/authentication.service';
import { Router } from '@angular/router';
import { Validators, UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hidePassword = true;

  loginForm = new UntypedFormGroup({
    email: new UntypedFormControl('', Validators.required),
    password: new UntypedFormControl('', Validators.required),
  });

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.authenticationService.login({ ...this.loginForm.value }).subscribe({
      next: () => {
        this.router.navigate(['/user/new']);
      },
      error: (error) => {
        alert("Usuário ou senha incorretos.\nPor favor, verfique suas credenciais e tente novamente.");
      }
    });
  }

}
