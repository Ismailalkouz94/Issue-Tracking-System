import { Component, OnInit,ViewChild} from '@angular/core';
import { IsseService } from '../_services/isse.service';
import { AgGridAngular } from 'ag-grid-angular';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  public currentUser;
  issues: any;

  paginationPageSize = 10;
  columnDefs = [
    { headerName: '#', field: 'id' ,sortable: true,filter: true,checkboxSelection: true },
    { headerName: 'Title', field: 'title',sortable: true,filter: true },
    { headerName: 'Descption', field: 'descption',sortable: true ,filter: true},
    { headerName: 'Attachment', field: 'attachment',sortable: true ,filter: true},
    { headerName: 'Owner', field: 'user.firstName',sortable: true ,filter: true},
    { headerName: 'Assigend To', field: 'assignTo.firstName',sortable: true ,filter: true},
    { headerName: 'Status', field: 'status.name',sortable: true ,filter: true},
    { headerName: 'Type', field: 'type.neme',sortable: true ,filter: true}
  ];

  defaultColDef = {
    flex: 1,
    minWidth: 100,
    resizable: true,
    sortable: true,
  };

  rowData ;

  constructor(private issueService: IsseService) {
    this.currentUser = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '';

  }

  ngOnInit() {


    this.issueService.getIssues(this.currentUser.id).subscribe(
      (data) => {
        console.log("data",data.response);
        if (data.success) {
          this.issues = data.response;
          this.rowData=data.response;
        } else {

        }
      },
      (error) => {
        console.log(error);

      }
    )

  }

}
