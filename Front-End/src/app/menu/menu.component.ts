import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User, Role, RoleEnum } from '../_models';
import { AuthenticationService } from '../_services';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
  }

  get isAdmin() {
    let role: Role;
    role = this.currentUser.role;

    return this.currentUser && this.currentUser.role.id === RoleEnum.Admin;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }



}
