import { Component, OnInit, ViewChild } from '@angular/core';
import { IssueService } from '../_services/issue.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  public currentUser;
  issues: any;
  private gridApi;
  rowData;
  filterId = 1;

  paginationPageSize = 10;
  columnDefs = [
    { headerName: '#', field: 'id', sortable: true, filter: true, checkboxSelection: true },
    { headerName: 'Title', field: 'title', sortable: true, filter: true },
    { headerName: 'Desciption', field: 'description', sortable: true, filter: true },
    // { headerName: 'Attachment', field: 'attachment', sortable: true, filter: true },
    { headerName: 'Owner', field: 'user.firstName', sortable: true, filter: true },
    { headerName: 'Assigend To', field: 'assignTo.firstName', sortable: true, filter: true },
    { headerName: 'Status', field: 'status.name', sortable: true, filter: true },
    { headerName: 'Type', field: 'type.neme', sortable: true, filter: true },

  ];

  // <i class="icon-trash cell-btn-remove" title="Delete this record" ng-click="deleteRecord(data,'+params.node.id+')">

  defaultColDef = {
    flex: 1,
    minWidth: 100,
    resizable: true,
    sortable: true,
  };

  constructor(private issueService: IssueService,
    private toastr: ToastrService,
    private router: Router) {
    this.currentUser = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '';

  }

  ngOnInit() {
    // this.getUserAssignedIssues();
    this.getUserIssues();
  }

  onSelectionChanged(event) {
    this.gridApi = event.api.selectionController;
  }

  onGridReady(params) {
  }


  getUserIssues() {
    this.issueService.getIssues(this.currentUser.id).subscribe(
      (data) => {
        if (data.success) {
          this.rowData = data.response;
        } else {

        }
      },
      (error) => {
      }
    )
  }

  getUserAssignedIssues() {
    this.issueService.getAssignedIssues(this.currentUser.id).subscribe(
      (data) => {
        if (data.success) {
          this.rowData = data.response;
        } else {

        }
      },
      (error) => {
      }
    )
  }

  deleteRow() {
    if (this.gridApi.getSelectedRows()[0].user.id != this.currentUser.id) {
      this.toastr.error("Owner Only Can Delete Issues", 'Error');
    } else {
      this.issueService.deleteIssue(this.gridApi.getSelectedRows()[0].id).subscribe(
        (data) => {
          if (data.success) {
            // this.rowData = data.response;
            this.toastr.success(data.successMsg, 'Success');
            this.onFilterClick(this.filterId);
            // this.getUserIssues();

          } else {
            this.toastr.error(data.errMsg, 'Error');
          }
        },
        (error) => {
          this.toastr.error(error.error.message, 'Error');
        }
      )
    }
  }

  goUpdate() {
    this.router.navigate([`/createIssue/${this.gridApi.getSelectedRows()[0].id}`]);
  }

  onFilterClick(value) {
    this.filterId = value;
    this.issueService.issuesFilter(this.currentUser.id, value).subscribe(
      (data) => {
        if (data.success) {
          this.rowData = data.response;
        } else {

        }
      },
      (error) => {
      }
    )

  }

}
