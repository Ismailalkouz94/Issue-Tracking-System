import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  addUser(user: User) {
    return this.http.post<any>(`users`, user);
  }

  editUser(id,user: User) {
    console.log(user);
    return this.http.put<any>(`users/${id}`, user);
  }

  getUserRoles() {
    return this.http.get<any>(`users/roles`);
  }

  getUserPostions() {
    return this.http.get<any>(`users/postions`);
  }

  getAllUsers() {
    return this.http.get<any>(`users`);
  }

  getUserById(id) {
    return this.http.get<any>(`users/id/${id}`);
  }

  deleteUser(id) {
    return this.http.delete<any>(`users/${id}`);
  }

}