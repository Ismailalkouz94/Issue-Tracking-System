import { Component, OnInit, ViewChild } from '@angular/core';
import { IssueService } from '../_services/issue.service';
import { AgGridAngular } from 'ag-grid-angular';
import { ToastrService } from 'ngx-toastr';

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

  paginationPageSize = 10;
  columnDefs = [
    { headerName: '#', field: 'id', sortable: true, filter: true, checkboxSelection: true },
    { headerName: 'Title', field: 'title', sortable: true, filter: true },
    { headerName: 'Descption', field: 'descption', sortable: true, filter: true },
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

  constructor(private issueService: IssueService, private toastr: ToastrService) {
    this.currentUser = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '';

  }

  ngOnInit() {
    this.getUserAssignedIssues();
  }

  onSelectionChanged(event) {
    this.gridApi = event.api.selectionController;
    console.log(event.api.selectionController.getSelectedRows()[0].title);
  }

  onGridReady(params) {
    console.log(params);
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
        console.log(error);
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
        console.log(error);
      }
    )
  }

  deleteRow() {
    console.log(this.gridApi.getSelectedRows()[0].title)

    this.issueService.deleteIssue(this.gridApi.getSelectedRows()[0].id).subscribe(
      (data) => {
        console.log("data", data.response);
        if (data.success) {
          // this.rowData = data.response;
          this.toastr.success(data.successMsg, 'Success');
          this.getUserIssues();

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
