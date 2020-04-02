import { Component, OnInit } from '@angular/core';
import { IsseService } from '../_services/isse.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {
  public currentUser;
  issues: any;
  constructor(private issueService: IsseService) {
    this.currentUser = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '';

  }

  ngOnInit() {


    this.issueService.getIssues(this.currentUser.id).subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.issues = data.response;
        } else {

        }
      },
      (error) => {
        console.log(error);

      }
    )

  }

}
