import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Issue } from '../_models';


@Injectable({
  providedIn: 'root'
})
export class IsseService {

  constructor(private http: HttpClient) { }

  getIssues(id: number) {
    return this.http.get<any>(`issue/find/user/` + id, {})
      .pipe(map(user => {
        return user;
      }));
  }

  getAssignedIssues(id: number) {
    return this.http.get<any>(`issue/find/assinged/` + id, {})
      .pipe(map(user => {
        return user;
      }));
  }

  deleteIssue(id: number) {
    return this.http.post<any>(`issue/delete/` + id, {})
      .pipe(map(user => {
        return user;
      }));
  }

  getIssueTypes() {
    return this.http.get<any>(`issue/find/type`)
      .pipe(map(data => {
        return data;
      }));
  }

  createIssue(issue: Issue) {
    console.log(issue);
    return this.http.post<any>(`issue/add`, issue)
      .pipe(map(data => {
        return data;
      }));
  }


}
