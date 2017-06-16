import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit,Input, OnDestroy, EventEmitter } from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Headers, Http,Response,RequestOptions } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.css']
})
export class FieldComponent implements OnInit, OnDestroy {

 @Input() selectedDocType;

  constructor(fb: FormBuilder,private ds:DocumentService,private http: Http) {
        this.form = fb.group({
            "fieldName": this.fieldName,
            "dataType": this.fieldType,
            "fieldPossibleValue": this.fieldPossibleValue,
            "sectionName": this.fieldSec,
         
        });

    }
   form: FormGroup;
    fieldName = new FormControl('',Validators.required)
    fieldType = new FormControl('Radio',Validators.required)
    fieldPossibleValue = new FormControl('',Validators.required)
    fieldSec = new FormControl('Document Section 1',Validators.required)
    
    public fieldList =[];
    // public fieldinput: boolean=false;
    public selectdatatype: boolean=true;
    public updated = new EventEmitter<boolean>()

  ngOnDestroy(){
    console.log("Field component Destroying...")
  }
  ngOnInit() {
  
    // this.ds.documentType.subscribe(data => {
    //    console.log("Data received in field component : "+JSON.stringify(data))
    //    this.selectedDocType = data
    // })
    console.log("ng------"+JSON.stringify(this.selectedDocType));
    
  }

// fielddata(){

   
//     this.fieldinput =true;
    
// }



dataTypeChange(event:any){
  console.log();
  if(event.target.value =="Text"){
    this.selectdatatype =false;
    return
  }
   this.selectdatatype =true;
   
  
}

onSubmit(){
    // console.log("this.selectedDocType --> "+JSON.stringify(this.	));
    this.form.addControl("documentTypeId", new FormControl(this.selectedDocType.documentTypeId))
       
    console.log("Form data on submit : "+JSON.stringify(this.form.value))
     let body = JSON.stringify(this.form.value);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers,method : 'POST'});

 
     this.http.post('http://localhost:8080/field/create', body,options).
        map(res=>res.json()).
        subscribe((res) => {
            console.log("response data: "+JSON.stringify(res))
            // this.updated.emit(true)
            this.ds.updateChild()
            console.log("Done Emitting !!")
        });  
        this.form.reset();
        // this.fieldinput =false;   
}

}

