import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {MainViewComponent} from './Components/Views/main-view/main-view.component';
import {UserBarComponent} from './Components/SetsOfAtomicComponents/user-bar/user-bar.component';
import {RegisterScreenComponent} from './Components/Views/register-screen/register-screen.component';

const routes: Routes = [
    {path: '', redirectTo: 'start', pathMatch: 'full'},
    {path: 'start', component: MainViewComponent, pathMatch: 'full'},
    {path: 'start/:id', component: MainViewComponent, pathMatch: 'full'},
    {path: 'courses', component: CourseBarComponent},
    {path: 'threads', component: ThreadBarComponent},
    {path: 'users', component: UserBarComponent},
    {path: 'login', component: LoginScreenComponent},
    {path: 'register', component: RegisterScreenComponent},
    {path: '**', redirectTo: ''}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
