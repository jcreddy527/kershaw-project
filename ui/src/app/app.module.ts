import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { AppComponent }  from './app.component';
import { HomeComponent } from './home/home.component';
import { DealComponent } from './deal/deal.component';
import { LoanFormComponent } from './loanform/loanform.component';
import { TreeModule } from 'angular-tree-component';
import { FullTreeComponent } from './fulltree/fulltree.component';
import { SectionFormComponent } from './sectionform/sectionform.component';
import { DocumentService }from './services/document.service';
import { DealService } from './deal/deal.service';
import { FieldComponent } from './field/field.component';
import { FullTreeService } from './fulltree/fulltree.service';
import { SharedService } from './services/sharedService.service';
import { TreeNode, TREE_ACTIONS, KEYS, IActionMapping } from 'angular-tree-component';


/* Feature Modules */

@NgModule({
  imports: [
    ReactiveFormsModule,
    FormsModule,
    TreeModule,
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
      { path: 'home', component: HomeComponent },
      {path: 'dealSections/:dealComponent', component: DealComponent },
      { path: 'dealSections', component: DealComponent},
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: '**', redirectTo: 'home', pathMatch: 'full' }
    ]),
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    DealComponent,
    FieldComponent,
    FullTreeComponent, 
    LoanFormComponent,
    SectionFormComponent
  ],
  providers: [ DocumentService,FullTreeService,DealService,SharedService ],

  bootstrap: [ AppComponent ]
})
export class AppModule { }