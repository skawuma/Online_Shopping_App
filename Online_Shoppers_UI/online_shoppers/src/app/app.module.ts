import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { UserService } from './services/user.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './_auth/auth.interceptor';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { MatButtonModule } from '@angular/material/button';

 import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';

 import { MatInputModule } from '@angular/material/input';

 import { MatToolbarModule } from '@angular/material/toolbar';
import { AddNewproductComponent } from './add-newproduct/add-newproduct.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { DragDirective } from './drag.directive';
import { ShowProductDetailsComponent } from './show-product-details/show-product-details.component';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';



@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ForbiddenComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    UserComponent,
    AddNewproductComponent,
    DragDirective,
    ShowProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule, 
    MatGridListModule,
    MatTableModule,
    MatIconModule
    
    // NgbModule.forRoot()
  ],
  providers: [
  
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
