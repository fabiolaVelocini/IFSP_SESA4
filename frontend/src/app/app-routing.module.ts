import { NavigationComponent } from './shared/components/navigation/navigation.component';
import { HomeComponent } from './home/components/home/home.component';
import { FirstAccessComponent } from './authentication/components/first-access/first-access.component';
import { LoginComponent } from './authentication/components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  { path: 'first-access', component: FirstAccessComponent },
  { path: '', component: NavigationComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'user', loadChildren: () => import('./user/user.module').then((m) => m.UserModule) },
    { path: 'email', loadChildren: () => import('./email/email.module').then((m) => m.EmailModule) },
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
