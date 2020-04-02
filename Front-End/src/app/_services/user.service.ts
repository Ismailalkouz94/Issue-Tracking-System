import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  register(user: User) {
    console.log(user);
      return this.http.post<any>(`user/add`, user)
      .pipe(map(data => {
        return data;
    }));
  }

  getUserRoles() {
      return this.http.get<any>(`user/find/role`)
      .pipe(map(data => {
        return data;
    }));
  }

  getUserPostions() {
    return this.http.get<any>(`user/find/postion`)
    .pipe(map(data => {
      return data;
  }));
}


}