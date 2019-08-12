import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {StandardViewComponent} from './Components/Views/standard-view/standard-view.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';

const routes: Routes = [
    {path: 'courses', component: CourseBarComponent},
    {path: 'threads', component: ThreadBarComponent},
    {path: 'login', component: LoginScreenComponent},
    {path: '', component: StandardViewComponent}

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
