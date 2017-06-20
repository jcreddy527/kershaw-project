import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import { DealService } from '../deal/deal.service'

@Component({
    selector: 'app-loanform',    
    templateUrl: './loanform.component.html'
})
export class LoanFormComponent implements OnInit, OnDestroy {

    @Input('documentID')
    documentID: number

    ngOnInit(): void{ 
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.documentID).subscribe(
                jsondata => {
                    this.formElements=jsondata,
                    this.FillFields();
                //    console.log("this.fields : "+JSON.stringify(this.formElements))
                error => console.log("Error: "+error)
        })                   
    }

    ngOnDestroy(){
        console.log("Loan component destroying")
    }

    dataEntryForm: FormGroup
    formElements: any[]=[]
    fields: any[]=[]

    constructor(private fb: FormBuilder, private dealService : DealService){}

    FillFields(){
        for(var i=0;i<this.formElements.length;i++){
            this.fields.push(this.formElements[i].fields)
            this.formElements[i].fields.forEach(fld => {
              this.dataEntryForm.addControl(fld.fieldName,new FormControl(fld.fieldValue))
            })
        }
        // console.log("AAAAAA : "+ JSON.stringify(this.fields))
    }

    save(){
         console.log(this.dataEntryForm.value)
        // console.log(JSON.stringify(this.dataEntryForm))
        // // console.log('Saved: '+ JSON.stringify(this.dataEntryForm.value))
        // this.dealService.postDealDetails(this.dataEntryForm.value);
    }
}