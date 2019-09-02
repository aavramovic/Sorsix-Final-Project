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
import {LoginScreenComponent} from './Components/Views/login-screen/login-screen.component';
import {CourseBarComponent} from './Components/SetsOfAtomicComponents/course-bar/course-bar.component';
import {ThreadBarComponent} from './Components/SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatIconModule,
    MatRadioModule,
    MatSelectModule,
    MatNativeDateModule, MatCardModule, MatAutocompleteModule, MatSnackBar, MatSnackBarContainer, MatSnackBarModule
} from '@angular/material';
import {RegisterScreenComponent} from './Components/Views/register-screen/register-screen.component';
import {UserBarComponent} from './Components/SetsOfAtomicComponents/user-bar/user-bar.component';
import {MatSelectComponent} from './Components/AtomicComponents/mat-select/mat-select.component';
import {AdminComponent} from './Components/Views/admin/admin.component';
import {NewCourseComponent} from './Components/AtomicComponents/new-course/new-course.component';
import {MatDialogModule} from '@angular/material';
import {SearchUserComponent} from './Components/Views/admin/search-user/search-user.component';
import {UserDetailsComponent} from './Components/Views/user-details/user-info/user-details.component';
import {UserPostsComponent} from './Components/Views/user-details/user-posts/user-posts.component';
import {UserPostsLikedComponent} from './Components/Views/user-details/user-posts-liked/user-posts-liked.component';
import {UserFollowingsComponent} from './Components/Views/user-details/user-followings/user-followings.component';
import {UserFollowersComponent} from './Components/Views/user-details/user-followers/user-followers.component';
import {NewPostComponent} from './Components/AtomicComponents/new-post/new-post.component';
import {NgxPaginationModule} from 'ngx-pagination';

import {OverlayModule} from '@angular/cdk/overlay';
import {NewReplyComponent} from './Components/AtomicComponents/new-reply/new-reply.component';

@NgModule({
    declarations: [
        AppComponent,
        CourseComponent,
        ThreadComponent,
        MenuBarComponent,
        MainViewComponent,
        LoginScreenComponent,
        CourseBarComponent,
        ThreadBarComponent,
        RegisterScreenComponent,
        UserBarComponent,
        MatSelectComponent,
        AdminComponent,
        NewCourseComponent,
        SearchUserComponent,
        UserDetailsComponent,
        UserDetailsComponent,
        UserPostsComponent,
        UserPostsLikedComponent,
        UserFollowingsComponent,
        UserFollowersComponent,
        SearchUserComponent,
        NewPostComponent,
        NewReplyComponent
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
        MatDialogModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatCardModule,
        NgxPaginationModule,
        MatAutocompleteModule,
        OverlayModule,
        MatSnackBarModule
    ],
    providers: [MatDatepickerModule],
    bootstrap: [AppComponent],
    entryComponents: [NewCourseComponent, NewPostComponent, NewReplyComponent]
})
export class AppModule {
}
