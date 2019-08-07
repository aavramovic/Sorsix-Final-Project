import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {StandardViewComponent} from './Components/Views/standard-view/standard-view.component';
import {ThreadComponent} from './Components/AtomicComponents/thread/thread.component';


const routes: Routes = [
    {path: '', component: StandardViewComponent},
    {path: 'test/thread', component: ThreadComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
