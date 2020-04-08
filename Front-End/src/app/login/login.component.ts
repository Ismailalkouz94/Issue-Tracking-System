import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

import { AuthenticationService } from '../_services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });

    //check if there error from othher components
    if (this.route.snapshot.paramMap.get("isError")) {
      this.toastr.error(this.route.snapshot.paramMap.get("errDetail"), this.route.snapshot.paramMap.get("errMsg"));
    }

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }


  // for accessing to form fields
  get fval() { return this.loginForm.controls; }

  onFormSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.fval.userName.value, this.fval.password.value)
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          // console.log(error.error.errMsg)
          this.toastr.error(error.error.errMsg, 'Error');
          this.loading = false;
        });
  }
}
