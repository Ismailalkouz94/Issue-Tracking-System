import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  register(user: User) {
    console.log(user);
    return this.http.post<any>(`users/add`, user);
  }

  getUserRoles() {
    return this.http.get<any>(`users/role`);
  }

  getUserPostions() {
    return this.http.get<any>(`users/postion`);
  }

  getAllUsers() {
    return this.http.get<any>(`users`);
  }


}