import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';

const routes: Routes = [
    {path: 'courses', component: CourseBarComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
