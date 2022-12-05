import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
  ) { }

  createUser(user: Object) {
    return this.http.post<Object>(`${environment.apiUrl}/user`, user);
  }
}
