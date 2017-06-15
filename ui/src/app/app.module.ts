import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { AppComponent }  from './app.component';
import { HomeComponent } from './home/home.component';
import { DealComponent } from './deal/deal.component';
import { DocumentService }from './services/document.service';
import { TreeModule } from 'angular-tree-component';
import { FullTreeComponent } from 'app/fulltree/fulltree.component';
import { DealService } from './deal/deal.service';
import { FullTreeService } from './fulltree/fulltree.service';

/* Feature Modules */

@NgModule({
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    HttpModule,
    FormsModule,
    TreeModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: 'home', component: HomeComponent },
      { path: 'dealSections', component: DealComponent},
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: '**', redirectTo: 'home', pathMatch: 'full' }
    ]),
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    DealComponent,
    FullTreeComponent
  ],
  providers: [ DocumentService,FullTreeService,DealService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
