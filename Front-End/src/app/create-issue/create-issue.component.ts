import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, AuthenticationService } from '../_services';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IssueService } from '../_services/issue.service';
import { Issue, User } from '../_models';

@Component({
  selector: 'app-create-issue',
  templateUrl: './create-issue.component.html',
  styleUrls: ['./create-issue.component.css']
})
export class CreateIssueComponent implements OnInit {
  public currentUser:User;
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  issueType;
  issueStatus;
  users;
  id;
  issue: Issue;
  assignToId;
  typeId;
  statusId;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private issueService: IssueService,
    private toastr: ToastrService,
    private authenticationService:AuthenticationService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
    console.log(this.users);

    this.issue = new Issue(1, "test", "test", 1, 1, 1, 1);

    this.id = this.route.snapshot.params['id'];

    this.registerForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      owner: ['',],
      assignTo: ['', Validators.required],
      type: ['', Validators.required],
      status: ['',],
    });

    this.getUsers();
    this.getIssueType();
    if (this.id != -1) {
      this.getIssueById();
      this.getIssueStatus();
    }

  }

  get fval() { return this.registerForm.controls; }


  onFormSubmit() {
    this.submitted = true;
    // return for here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.registerForm.controls.owner.setValue(this.currentUser.id);

    if (this.id == -1) {
      this.create();
    } else {
      this.update()
    }

  }

  create() {
    this.issueService.createIssue(this.registerForm.value).subscribe(
      (data) => {
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
        this.toastr.error(error.error.message, 'Error');
        this.loading = false;
      }
    )
  }

  update() {
    this.issueService.updateIssue(this.id,this.registerForm.value).subscribe(
      (data) => {
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
      }
    )
  }

  getIssueStatus() {
    this.issueService.getIssueStatus().subscribe(
      (data) => {
        if (data.success) {
          this.issueStatus = data.response;
        } else {

        }
      },
      (error) => {
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
      }
    )
  }

  getIssueById() {
    this.issueService.getIssueById(this.id).subscribe(
      (data) => {
        if (data.success) {
          this.issue = data.response;
          this.assignToId=data.response.assignTo.id;
          this.typeId=data.response.type.id;
          this.statusId=data.response.status.id;
        } else {
        }
      },
      (error) => {
      }
    )
  }



}


