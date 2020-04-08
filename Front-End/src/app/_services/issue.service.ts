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
    return this.http.get<any>(`issues/user/${id}`);
  }

  getAssignedIssues(id: number) {
    return this.http.get<any>(`issues/assinged/${id}`);
      // .pipe(map(user => {
      //   return user;
      // }));
  }

  deleteIssue(id: number) {
    return this.http.delete<any>(`issues/${id}`);
  }

  getIssueTypes() {
    return this.http.get<any>(`issues/type`);
  }

  createIssue(issue: Issue) {
    return this.http.post<any>(`issues/add`, issue);
  }


}
