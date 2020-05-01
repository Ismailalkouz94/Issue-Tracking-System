import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, AuthenticationService } from '../_services';
import { User } from '../_models';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['id', 'userName','firstName','lastName', 'email','role','edit','delete'];
  dataSource = new MatTableDataSource<User>();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  users
  public currentUser: User;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService,

  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {

    this.getUsers();
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }

  getUsers() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        if (data.success) {
          this.users = data.response;
          this.dataSource.data = data.response;
        } else {

        }
      },
      (error) => {
      }
    )
  }

  deleteUser(id) {
    this.userService.deleteUser(id).subscribe(
      (data) => {
        if (data.success) {
          this.getUsers();
          this.toastr.success(data.successMsg, 'Success');
        } else {
          this.toastr.error(data.errMsg, 'Error');
        }
      },
      (error) => {
      }
    )
  }

  editUser(id){
    this.router.navigate([`/register/${id}`]);
  }

}

