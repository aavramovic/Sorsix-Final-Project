import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {CourseComponent} from './Components/AtomicComponents/course/course.component';
import {ThreadComponent} from './Components/AtomicComponents/thread/thread.component';
import {RouterModule} from '@angular/router';
import {MenuBarComponent} from './Components/Views/menu-bar/menu-bar.component';
import {MainViewComponent} from './Components/Views/main-view/main-view.component';
import {UserComponent} from './Components/AtomicComponents/user/user.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule, MatButtonToggleModule, MatCheckboxModule, MatIconModule, MatRadioModule, MatSelectModule} from '@angular/material';
import {RegisterScreenComponent} from './Components/Views/register-screen/register-screen.component';
import {UserBarComponent} from './Components/SetsOfAtomicComponents/user-bar/user-bar.component';
import {MatSelectComponent} from './Components/AtomicComponents/mat-select/mat-select.component';
<<<<<<< HEAD
import { AdminComponent } from './Components/Views/admin/admin.component';
import { NewCourseComponent } from './new-course/new-course.component';
=======
import {AdminComponent} from './Components/Views/admin/admin.component';
import {NewCourseComponent} from './Components/Views/new-course/new-course.component';
import {MatDialogModule} from '@angular/material';
>>>>>>> 46207e2637a9154654720792cd92411daf6ebde1

@NgModule({
    declarations: [
        AppComponent,
        CourseComponent,
        ThreadComponent,
        MenuBarComponent,
        MainViewComponent,
        UserComponent,
        LoginScreenComponent,
        CourseBarComponent,
        ThreadBarComponent,
        RegisterScreenComponent,
        UserBarComponent,
        MatSelectComponent,
        AdminComponent,
        NewCourseComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AppRoutingModule,
        HttpClientModule,
        RouterModule,
        FormsModule,
        BrowserAnimationsModule,
        MatProgressSpinnerModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatCheckboxModule,
        MatRadioModule,
        MatDialogModule
    ],
    providers: [],
    bootstrap: [AppComponent],
    entryComponents: [NewCourseComponent]
})
export class AppModule {
}
