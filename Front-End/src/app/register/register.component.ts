import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { UserService } from '../_services/user.service';
import { User, Position, Role } from '../_models';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  roles;
  postions;
  userId;
  user;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private toastr: ToastrService,
    private route: ActivatedRoute,
  ) { }


  ngOnInit() {

    this.user=new User(1,"test","test","test","test","test",1,1,new Position(1,"test","test"),new Role(1,"test"));
    this.userId = this.route.snapshot.params['id'];

    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      userName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      roleId: ['', Validators.required],
      positionId: ['', Validators.required]
    });

    this.getRoles();
    this.getPostions();
    if (this.userId != -1) {
      this.getUserById(this.userId);
      this.registerForm.controls.password.setValue("******");
    }

  }

  get fval() { return this.registerForm.controls; }

  onFormSubmit() {
    this.submitted = true;
    // return for here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    if (this.userId == -1) {
      this.addUser();
    } else {
      this.editUser();
    }

  }

  addUser() {
    this.userService.addUser(this.registerForm.value).subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.toastr.success(data.successMsg, 'Success');
          this.loading = false;
          this.router.navigate(['/users']);
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

  editUser() {
    this.userService.editUser(this.userId,this.registerForm.value).subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.toastr.success(data.successMsg, 'Success');
          this.loading = false;
          this.router.navigate(['/users']);
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

  getUserById(id){
    this.userService.getUserById(id).subscribe(
      (data) => {
        if (data.success) {
          this.user = data.response;
        } else {

        }
      },
      (error) => {

      }
    )
  }


  getRoles() {
    this.userService.getUserRoles().subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.roles = data.response;
        } else {

        }
      },
      (error) => {

      }
    )
  }


  getPostions() {
    this.userService.getUserPostions().subscribe(
      (data) => {
        console.log(data);
        if (data.success) {
          this.postions = data.response;
        } else {

        }
      },
      (error) => {

      }
    )
  }


}
