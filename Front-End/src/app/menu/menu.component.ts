import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User, Role, RoleEnum } from '../_models';
import { AuthenticationService } from '../_services';
import { ModalService } from '../_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: User;
  bodyText: string;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private modalService: ModalService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      oldPassword: ['', Validators.required],
      newPassword:['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get fval() { return this.loginForm.controls; }

  get isAdmin() {
    let role: Role;
    role = this.currentUser.role;

    return this.currentUser && this.currentUser.role.id === RoleEnum.Admin;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
    this.loginForm.reset();
  }

  onChangePasswordSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.changePassword(this.currentUser.id, this.loginForm.value)
      .subscribe(
        data => {
          if (data.success) {
            this.toastr.success(data.successMsg, 'Success');
            this.loading = false;
            this.closeModal('custom-modal-1');
          } else {
            this.toastr.error(data.errMsg, 'Error');
            this.loading = false;
          }
        
        },
        error => {
          // console.log(error.error.errMsg)
          this.toastr.error(error.error.errMsg, 'Error');
          this.loading = false;
        });
  }

}
