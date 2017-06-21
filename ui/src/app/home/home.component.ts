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
    
    // docTypeName = new FormControl("", Validators.required);
   
    constructor(fb: FormBuilder,private ds:DocumentService,private http: Http) {
         this.form = fb.group({
            'documentType': new FormControl("", Validators.required)
        });
        
        // this.form.documentType.valueChanges.subscribe(value => {
        //     console.log("Value Changed : "+value)
        // })  

    }
    docType(event:any){
      this.fieldinput = false;  
      this.docTypeList.forEach(docType => {
          if(docType && docType.documentTypeId == event.target.value){
              this.selectedDocType = docType               
            //  console.log("Selected Doctype : "+JSON.stringify(this.selectedDocType))
              return;
          }
      })  
    //   console.log(event)
    //   this.selectedDocType = event.target.value;
    this.docFieldList =this.selectedDocType.fields;
     // console.log("data===>"+JSON.stringify(this.selectedDocType));
    }
    ngOnInit() {
        this.ds.getAllDocumentTypes().subscribe(jsondata => {
            this.docTypeList=jsondata 
            this.selectedDocType =  this.docTypeList[0] 
            this.docFieldList =this.selectedDocType.fields; 
           // console.log("this.selectedDocType --> "+ JSON.stringify(this.selectedDocType))
            this.ds.setDocumentType(this.selectedDocType)   
        },  error => this.errorMessage = <any>error);        

        this.ds.childUpdated.subscribe(data => {
            this.ds.getAllDocumentTypes().subscribe(jsondata => {
                this.docTypeList=jsondata 
                this.selectedDocType =  this.docTypeList[0] 
                this.docFieldList =this.selectedDocType.fields; 
                //console.log("this.selectedDocType --> "+ JSON.stringify(this.selectedDocType))
                this.ds.setDocumentType(this.selectedDocType)   
                this.fieldinput = false;
            },  error => this.errorMessage = <any>error);               
        })  
             
     }

    docName(){
        //str ="{"name":this.docTypeName}";
    // this.ds.saveDocName(str);
        this.nameEnabled =true;
    }

     addField(){
        this.fieldinput = true;
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
        post('http://localhost:8080/documentType/create', body, options).
        map(res=>res.json()).
        subscribe((res) => console.log(res));
     //   console.log(body);

    this.nameEnabled =false;
 
    this.ds.getAllDocumentTypes().subscribe(jsondata => {
            this.docTypeList=jsondata   
           // console.log("data--->"+jsondata);
          this.ds.updateChild()    
        },  error => this.errorMessage = <any>error);   

    this.form.reset();
    
    }
     
    childUpdated(event: Event){
         console.log(event)
         if(status){
             this.ds.getAllDocumentTypes().subscribe(jsondata => {
                    this.docTypeList=jsondata 
                    this.selectedDocType =  this.docTypeList[0] 
                    this.docFieldList =this.selectedDocType.fields; 
                  //  console.log("this.selectedDocType --> "+ JSON.stringify(this.selectedDocType))
                    this.ds.setDocumentType(this.selectedDocType)   
            },  error => this.errorMessage = <any>error); 
         }
     }
}