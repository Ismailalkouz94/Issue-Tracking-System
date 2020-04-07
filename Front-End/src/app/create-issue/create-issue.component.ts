import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_services';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IssueService } from '../_services/issue.service';

@Component({
  selector: 'app-create-issue',
  templateUrl: './create-issue.component.html',
  styleUrls: ['./create-issue.component.css']
})
export class CreateIssueComponent implements OnInit {
  public currentUser;
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  issueType;
  users;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private issueService: IssueService,
    private toastr: ToastrService
  ) { 
    this.currentUser = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '';
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      title: ['', Validators.required],
      descrption: ['', Validators.required],
      owner: ['',],
      assignTo: ['', Validators.required],
      type: ['', Validators.required],
    });

    this.getUsers();
    this.getIssueType();
  }

  get fval() { return this.registerForm.controls; }


  onFormSubmit() {
    this.submitted = true;
    // return for here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.registerForm.controls.owner.setValue(this.currentUser.id);
    this.issueService.createIssue(this.registerForm.value).subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.toastr.success(data.successMsg, 'Success');
          this.loading = false;
          this.router.navigate(['/issue']);
        } else {
          this.toastr.error(data.errMsg, 'Error');
          this.loading = false;
        }
      },
      (error) => {
        console.log(error);
        this.toastr.error(error.error.message, 'Error');
        this.loading = false;
      }
    )

  }

  getIssueType() {
    this.issueService.getIssueTypes().subscribe(
      (data) => {
        if (data.success) {
          this.issueType = data.response;
        } else {

        }
      },
      (error) => {
        console.log(error);
      }
    )
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        if (data.success) {
          this.users = data.response;
        } else {

        }
      },
      (error) => {
        console.log(error);
      }
    )
  }



}


