import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class IsseService {

  constructor(private http: HttpClient) { }

  getIssues(id:number) {
    return this.http.get<any>(`issue/find/user/`+id, {})
    .pipe(map(user => {
      return user;
  }));
}
}
