import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(
    private http: HttpClient,
  ) { }

  sendEmail(email: Object) {
    return this.http.post<Object>(`${environment.apiUrl}/api/email`, email);
  }
}
