import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TopPostsComponent} from './Components/ContainerComponents/top-posts/top-posts.component';
import {HttpClientModule} from '@angular/common/http';
import {CourseComponent} from './Components/AtomicComponents/course/course.component';
import {ThreadComponent} from './Components/AtomicComponents/thread/thread.component';
import {RouterModule} from '@angular/router';
import {StandardViewComponent} from './Components/Views/standard-view/standard-view.component';
import {HeaderComponent} from './Components/ContainerComponents/1-header/header.component';
import {MenuBarComponent} from './Components/ContainerComponents/menu-bar/menu-bar.component';
import {MainViewComponent} from './Components/ContainerComponents/2-main-view/main-view.component';
import {FooterComponent} from './Components/ContainerComponents/3-footer/footer.component';
import {UserComponent} from './Components/AtomicComponents/user/user.component';
import {LoginBarComponent} from './Components/ContainerComponents/login-bar/login-bar.component';
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {FormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        TopPostsComponent,
        CourseComponent,
        ThreadComponent,
        StandardViewComponent,
        HeaderComponent,
        MenuBarComponent,
        MainViewComponent,
        FooterComponent,
        UserComponent,
        LoginBarComponent,
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
