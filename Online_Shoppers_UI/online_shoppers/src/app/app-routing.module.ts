import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AddNewproductComponent } from './add-newproduct/add-newproduct.component';
import { ShowProductDetailsComponent } from './show-product-details/show-product-details.component';
import { ProductResolveService } from './services/product-resolve.service';
import { ProductViewDetailsComponent } from './product-view-details/product-view-details.component';
import { BuyProductComponent } from './buy-product/buy-product.component';
import { BuyProductResolverService } from './services/buy-product-resolver.service';
import { OrderConfirmationComponent } from './order-confirmation/order-confirmation.component';
import { RegisterComponent } from './register/register.component';
import { CartComponent } from './cart/cart.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';

const routes: Routes = [
  { path: '',
   component: HomeComponent },
   { path: 'register',
   component: RegisterComponent },
  { path: 'admin',
   component: AdminComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'user',
   component: UserComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  {
    path: 'buyProduct', 
    component: BuyProductComponent, 
    canActivate: [AuthGuard], 
    data: { roles: ['User'] },

    resolve: {
      productDetails: BuyProductResolverService
    }


  },

  {
    path: 'cart', 
    component: CartComponent, 
    canActivate: [AuthGuard], 
    data: { roles: ['User'] }

  },

  {
    path: 'myOrders', 
    component: MyOrdersComponent, 
    canActivate: [AuthGuard], 
    data: { roles: ['User'] }

  },
  { path: 'login', 
  component: LoginComponent },
  { path: 'forbidden',
   component: ForbiddenComponent },
  {
    path: 'addNewproduct', 
    component: AddNewproductComponent, 
    canActivate: [AuthGuard], 
    data: { roles: ['Admin'] },

    resolve: {
      product: ProductResolveService
    }


  },
  {
    path: 'OrderConfirm', 
    component: OrderConfirmationComponent,
     canActivate: [AuthGuard], 
     data: { roles: ['User'] },
  },

  { path: 'showProductDetails', 
  component: ShowProductDetailsComponent, 
  canActivate: [AuthGuard], 
  data: { roles: ['Admin'] } },
  {
    path: 'productVeiwDetails', component: ProductViewDetailsComponent,
    resolve: {
      product: ProductResolveService

    }

  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
