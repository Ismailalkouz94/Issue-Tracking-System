import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../_models';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(userName: string, password: string) {
        return this.http.post<any>(`login?userName=` + userName + `&password=` + password, {})
            .pipe(map(user => {
                console.log(user.response.user)
                if (user && user.response.jwtResponse.token) {
                    // store user details in local storage to keep user logged in
                    localStorage.setItem('currentUser', JSON.stringify(user.response.user));
                    localStorage.setItem('token', user.response.jwtResponse.token);
                    this.currentUserSubject.next(user.response.user);
                }

                return user.response.user;
            }));
    }

    logout() {
        // remove user data from local storage for log out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('token');
        this.currentUserSubject.next(null);
    }

    changePassword(id: number, values) {
        return this.http.post<any>(`users/${id}/changePassword`, values);
      }
    
}