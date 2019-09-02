import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {MainViewComponent} from './Components/Views/main-view/main-view.component';
import {RegisterScreenComponent} from './Components/Views/register-screen/register-screen.component';
import {AdminComponent} from './Components/Views/admin/admin.component';
import {UserDetailsComponent} from './Components/Views/user-details/user-info/user-details.component';
import {AdminGuardService} from './services/guards/admin-guard.service';
import {LoginGuardService} from './services/guards/login-guard.service';

const routes: Routes = [
    {
        path: 'details/:username',
        component: UserDetailsComponent
    },
    {path: '', redirectTo: 'start', pathMatch: 'full'},
    {path: 'start', component: MainViewComponent, pathMatch: 'full'},
    {path: 'start/:id', component: MainViewComponent, pathMatch: 'full'},
    {path: 'courses', component: CourseBarComponent},
    {path: 'courses/:id', component: ThreadBarComponent},
    {path: 'threads', component: ThreadBarComponent},
    {path: 'details/:username', component: UserDetailsComponent},
    {path: 'login', component: LoginScreenComponent, canActivate: [LoginGuardService]},
    {path: 'register', component: RegisterScreenComponent, canActivate: [LoginGuardService]},
    {path: 'admin', component: AdminComponent, canActivate: [AdminGuardService]},
    {path: '**', redirectTo: ''},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
