import { AuthenticationService } from './../../services/authentication.service';
import { Validators, UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-first-access',
  templateUrl: './first-access.component.html',
  styleUrls: ['./first-access.component.scss']
})
export class FirstAccessComponent implements OnInit {

  hidePassword = true;
  hideConfirmPassword = true;
  isFirstAccess = false;
  read = true;

  email!: string;
  passwordToken!: string;

  loginForm = new UntypedFormGroup({
    password: new UntypedFormControl('', Validators.required),
    confirmPassword: new UntypedFormControl('', Validators.required),
  });

  constructor(
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParams.subscribe((params) => {
      this.isFirstAccess = params['isFirstAccess'] === 'true';
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      if(this.read) {
        this.email = params['email'];
        this.passwordToken = params['passwordToken'];
        this.read = false;
      }

      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: { passwordToken: null, email: null },
        replaceUrl: true,
      });

    });
  }

  doFirstAccess() {
    const passInfo = {
      email: this.email,
      password: this.loginForm.value.password,
      passwordToken: this.passwordToken,
    };

    this.authenticationService.doFirstAccess(passInfo).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: () => alert("Erro no primeiro acesso.")
    });
  }

}

