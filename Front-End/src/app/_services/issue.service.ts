import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Issue } from '../_models';


@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private http: HttpClient) { }

  getIssues(id: number) {
    return this.http.get<any>(`issue/user/${id}`)
      .pipe(map(user => {
        return user;
      }));
  }

  getAssignedIssues(id: number) {
    return this.http.get<any>(`issue/assinged/${id}`)
      .pipe(map(user => {
        return user;
      }));
  }

  deleteIssue(id: number) {
    return this.http.delete<any>(`issue/${id}`)
      .pipe(map(user => {
        return user;
      }));
  }

  getIssueTypes() {
    return this.http.get<any>(`issue/type`)
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
