import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
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

    public pageTitle: string = 'Document Types';
    public docTypeList =[];
    public fieldList =[];
    public nameEnabled: boolean=false;
    public fieldEnable: boolean=false;
    public fieldinput: boolean=false;
    public errorMessage: string;

    
    docTypeName = new FormControl("", Validators.required);
   
    constructor(fb: FormBuilder,private ds:DocumentService,private http: Http) {
        this.form = fb.group({
            "documentType": this.docTypeName
        });
    }
 
    ngOnInit() {
        this.ds.getAllDocumentTypes().subscribe(jsondata => {
            this.docTypeList=jsondata     
        },  error => this.errorMessage = <any>error);   

        if(this.fieldList.length>0){
        this.fieldEnable =true;  
        }
     }
    docName(){
        //str ="{"name":this.docTypeName}";
    // this.ds.saveDocName(str);
        this.nameEnabled =true;
    }
 
 docType(event:any){event.target.value};

    onSubmit(){
        let body = JSON.stringify(this.form.value);
            let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers,method : 'POST'});

    
        this.http.post('http://localhost:8080/documentType/create', body,options).map(res=>res.json()).subscribe((res) =>this.docTypeName = res);
        console.log(body);

    this.nameEnabled =false;

    this.ds.getAllDocumentTypes().subscribe(jsondata => {
            this.docTypeList=jsondata     
        },  error => this.errorMessage = <any>error);   

    }
     
}