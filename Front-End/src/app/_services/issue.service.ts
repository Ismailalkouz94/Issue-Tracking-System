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
    return this.http.get<any>(`issues/types`);
  }

  getIssueStatus() {
    return this.http.get<any>(`issues/status`);
  }

  createIssue(issue: Issue) {
    return this.http.post<any>(`issues`, issue);
  }

  updateIssue(id,issue: Issue) {
    return this.http.put<any>(`issues/${id}`, issue);
  }

  getIssueById(id: number) {
    return this.http.get<any>(`issues/${id}`);
  }

  issuesFilter(id: number, filterId: number) {
    return this.http.get<any>(`issues/users/${id}/filter/${filterId}`);
  }


}
