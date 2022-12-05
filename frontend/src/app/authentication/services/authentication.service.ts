import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

interface PasswordInfo {
  email: string;
  password: any;
  passwordToken: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(
    private http: HttpClient,
    private router: Router
    ) {}

  doFirstAccess(passwordInfo: PasswordInfo) {
    return this.http.put<any>(`${environment.apiUrl}/auth/first-access`, passwordInfo);
  }

  logout() {
    localStorage.removeItem('currentEmail');
    localStorage.removeItem('bearerToken');
    localStorage.removeItem('roleId');

    this.router.navigate(['/login']);
  }

  get currentEmail() {
    if( localStorage.getItem('currentEmail') == null ){
      return 'guest'
    } else {
      return (localStorage.getItem('currentEmail') as string);
    }
  }

  set currentEmail(currentEmail) {
    localStorage.setItem('currentEmail', currentEmail as string);
  }

  get bearerToken() {
    return localStorage.getItem('bearerToken');
  }

  set bearerToken(bearerToken) {
    localStorage.setItem('bearerToken', bearerToken as string);
  }

  get isAuthenticated(): boolean {
    return !!this.bearerToken;
  }

  login(loginInfo: { email: string; password: string }) {
    return this.http
      .post<string>(`${environment.apiUrl}/auth/login`, loginInfo, {
        responseType: 'text' as 'json',
      })
      .pipe(
        take(1),
        tap((token) => {
          this.currentEmail = loginInfo.email;
          this.bearerToken = token;
        })
      );
  }

}
