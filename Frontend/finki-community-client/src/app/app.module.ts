import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {CourseComponent} from './Components/AtomicComponents/course/course.component';
import {ThreadComponent} from './Components/AtomicComponents/thread/thread.component';
import {RouterModule} from '@angular/router';
import {StandardViewComponent} from './Components/Views/standard-view/standard-view.component';
import {MenuBarComponent} from './Components/ContainerComponents/menu-bar/menu-bar.component';
import {MainViewComponent} from './Components/ContainerComponents/main-view/main-view.component';
import {UserComponent} from './Components/AtomicComponents/user/user.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {FormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        CourseComponent,
        ThreadComponent,
        StandardViewComponent,
        MenuBarComponent,
        MainViewComponent,
        UserComponent,
        LoginScreenComponent,
        CourseBarComponent,
        ThreadBarComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AppRoutingModule,
        HttpClientModule,
        RouterModule,
        FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
