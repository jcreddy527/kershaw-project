import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent }  from './app.component';
import { HomeComponent } from './home/home.component';
import { DealComponent } from './deal/deal.component';
import {ModalModule} from 'ng2-modal'; 
import { DocumentService }from './services/document.service';

/* Feature Modules */

@NgModule({
  imports: [
    ReactiveFormsModule,
    ModalModule,
    FormsModule,
    BrowserModule,
    HttpModule,
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
    DealComponent
  ],
  providers: [ DocumentService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
