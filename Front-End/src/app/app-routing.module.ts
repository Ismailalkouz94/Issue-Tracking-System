import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './_helpers/auth.guard';

/**Componenets */
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { IssueComponent } from './issue/issue.component';
import { Role, RoleEnum } from './_models/user';
import { CreateIssueComponent } from './create-issue/create-issue.component';


const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [AuthGuard],
    data: {
      roles: [RoleEnum.Admin]
    }
  },
  {
    path: 'issue',
    component: IssueComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'createIssue',
    component: CreateIssueComponent,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: '' },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
