import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit,Input} from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Headers, Http,Response,RequestOptions } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
@Component({
    templateUrl: './home.component.html'
})
export class HomeComponent {
   
    form: FormGroup;
    public selectedDocType: any;
    public docTypeList =[];
    public docFieldList =[];
    public nameEnabled: boolean=false;
    public errorMessage: string;
    public docTypeExist: boolean;
    public fieldinput: boolean=false;
    
    constructor(fb: FormBuilder,private ds:DocumentService,private http: Http) {
         this.form = fb.group({
            'documentType': new FormControl("", Validators.required)
        });
    }
   
   httpUrl: string = '/api' + '/documentType/'

    docType(event:any){
      this.fieldinput = false;  
      this.docTypeList.forEach(docType => {
          if(docType && docType.documentTypeId == event.target.value){
              this.selectedDocType = docType 

              this.ds.getDocumentType( this.selectedDocType.documentTypeId)
                .subscribe(jsondata => {            
                    for(let data of jsondata) {
                        if(this.docFieldList == null)              
                            this.docFieldList = data.fields;
                        else
                        this.docFieldList.push(data.fields);
                        console.log("lenght"+ this.docFieldList.length);                                    
                    }            
                },  
                error => this.errorMessage = <any>error
                );                              
                return;
          }
      })     
        this.docFieldList =this.selectedDocType.fields;
    }

    ngOnInit() {
        this.ds.getAllDocumentTypes().subscribe(jsondata => {
            this.docTypeList=jsondata 
            this.selectedDocType =  this.docTypeList[0] 
            this.docFieldList =this.selectedDocType.fields;             
                this.ds.getDocumentType(this.selectedDocType.documentTypeId).subscribe(jsondata => {
                for(let data of jsondata) {
                    if(this.docFieldList == null)
                        this.docFieldList = data.fields;
                    else
                    this.docFieldList.push(data.fields);           
            }  
            },  error => this.errorMessage = <any>error);            
              
        },  error => this.errorMessage = <any>error);  
        this.ds.fieldAdded.subscribe(data => {   // this event will get fresh fields list
            console.log("New field list ---"+JSON.stringify(data))
            this.docFieldList = data
        })
        this.ds.hideFieldForm.subscribe(flag => {  //this event will hide the form from child
            this.fieldinput = !flag;
        })
     }

    docName(){
        this.nameEnabled =true;
    }

     addField(){
        this.fieldinput = true;
        this.nameEnabled =false;
    }
 
    onSubmit(){
        this.docTypeExist = false;
        for(let data of this.docTypeList) {
            if(data.documentType == this.form.get('documentType').value){
                this.docTypeExist =true; 
                return;               
            }
        }
      
        let body = JSON.stringify(this.form.value);
            let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers,method : 'POST'});

        this.http.
        post(this.httpUrl+'create', body, options).
        map(res=>res.json()).
        subscribe((res) => {
            console.log(res)
            this.nameEnabled = false;
            this.ds.getAllDocumentTypes()
                    .subscribe(jsondata => {
                        this.docTypeList=jsondata              
                        this.ds.updateChild()  // to update the doctype list  
                        },  
                        error => this.errorMessage = <any>error
                    );                    
            this.form.reset();
        });        
    }
}