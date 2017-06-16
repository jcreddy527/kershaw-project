import{ Component,Input, OnInit } from '@angular/core'
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import {Http, Response} from '@angular/http'
import {Observable} from 'rxjs/Observable'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import { DealService } from './deal.service'


@Component({
    templateUrl: './deal.component.html'
})

export class DealComponent implements OnInit{  
     
    dataEntryForm: FormGroup
    formElements: any[] = []
    flag: boolean=true;
    
    constructor(private fb: FormBuilder, private _http: Http, private dealService : DealService){
    }

    ngOnInit(): void{       
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields().
            subscribe(
                jsondata => this.createForm(jsondata),
                error => console.log("Error: "+error)
        )                  
    }      

    createForm(jsonData: any){
        this.formElements = jsonData
        if(this.formElements==null || this.formElements.length==0){
            this.flag=false;
        }
        const group = this.fb.group({})        
        this.formElements.forEach(            
            formElment => {
                const formCtrl = new FormControl(formElment.value)        
                group.addControl(formElment.name, formCtrl)
            }
        )        
        this.dataEntryForm = group        

        this.formElements.forEach(
            formElement => this.setUpFormElement(formElement)
        )
    } 

    setUpFormElement(frmElem: any){
    }  

    save(){
        console.log("this.dataEntryForm "+this.dataEntryForm)
        console.log('Saved: '+ JSON.stringify(this.dataEntryForm.value))
        this.dealService.postDealDetails(this.dataEntryForm.value);
    }  
}   

    //  submitForm(form:NgForm){
    //        // console.log(this.employee)
    //         console.log(form.value)
    //         this.validatePrimaryLanguage(this.employee.primaryLanguage)
    //         if(this.hasPrimaryLanguageError)
    //             return;
    //         this.formPoster.postEmployeeForm(form.value)
    //         .subscribe(
    //             data => console.log('success: ',data),
    //             err => console.log('error: ',err))
    //     } 