import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services';
import { IsseService } from '../_services/isse.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  public currentUser;
  constructor(private issueService: IsseService,
    ) {
    this.currentUser = localStorage.getItem('currentUser')? JSON.parse(localStorage.getItem('currentUser')) : '';
   }

  ngOnInit() {


  }

}
