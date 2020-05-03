import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
 
import { ToastrModule } from 'ngx-toastr';
// services
import { InterceptorService } from './_services/interceptor.service';
import { UserService } from './_services/user.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { IssueComponent } from './issue/issue.component';
import { AgGridModule } from 'ag-grid-angular';
import { MatIconModule, MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule } from '@angular/material';
import 'ag-grid-enterprise';
import { CreateIssueComponent } from './create-issue/create-issue.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { UsersComponent } from './users/users.component';
import { CdkTableModule } from '@angular/cdk/table';
import { CommonModule } from '@angular/common';
import { ModalService } from './_services/modal/modal.service';
import { CustomModalComponent } from './_modals/custom-modal/custom-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    IssueComponent,
    CreateIssueComponent,
    MenuComponent,
    FooterComponent,
    UsersComponent,
    CustomModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
    AgGridModule.withComponents([]),
    MatIconModule,
    MatTableModule,
    CdkTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    CommonModule
  ],
  providers: [UserService,{ provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }], //
  bootstrap: [AppComponent]
})
export class AppModule { }
