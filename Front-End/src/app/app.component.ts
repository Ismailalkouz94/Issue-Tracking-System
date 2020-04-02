import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './_services';
import { User, Role, RoleEnum } from './_models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  get isAdmin() {
    let role:Role;
    role=this.currentUser.role;

    return this.currentUser && this.currentUser.role.id === RoleEnum.Admin;
}

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
}

issue() {
  this.router.navigate(['/issue']);
}

}
