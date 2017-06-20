import { Component, Input, OnInit, OnDestroy } from '@angular/core'
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import { Response } from '@angular/http'
import { Observable } from 'rxjs/Observable'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import { DealService } from './deal.service'
import { RouterModule, ActivatedRoute } from '@angular/router';



@Component({
    templateUrl: './deal.component.html'
})
export class DealComponent implements OnInit {

    dataEntryForm: FormGroup
    formElements: any[] = []
    flag: boolean = true;
    routedId: number;
    fields: any[] = []
    selectedDocumentId: string
    loanforms: string[] = []
    sectionForms: string[] = []
    constructor(private fb: FormBuilder, private dealService: DealService, private route: ActivatedRoute) {

    }

    ngOnDestroy() {
        console.log("Deal component destroying : " + this.routedId)
    }


    ngOnInit(): void {
        this.routedId = this.route.snapshot.params['dealComponent'];
        console.log("routed Id " + this.routedId)
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.routedId).
            subscribe(
            jsondata => {
                this.formElements = jsondata,
                    this.FillFields();

                console.log("this.fields : " + JSON.stringify(this.formElements))
                error => console.log("Error: " + error)
            })
        console.log("Done")
    }

    FillFields() {
        for (var i = 0; i < this.formElements.length; i++) {
            this.fields.push(this.formElements[i].fields)
        }
        console.log("AAAAAA : " + JSON.stringify(this.fields))
    }

    // createForm(jsonData: any){

    //     if(this.formElements==null || this.formElements.length==0){
    //         this.flag=true;
    //     }
    //     const group = this.fb.group({})        

    //     this.formElements.forEach(            
    //         formElment => {
    //             const formCtrl = new FormControl(formElment.fieldPossibleValues.fieldValue)        
    //             group.addControl(formElment.fields.fieldName, formCtrl)
    //         }
    //     )        
    //     this.dataEntryForm = group        

    //     this.formElements.forEach(
    //         formElement => this.setUpFormElement(formElement)
    //     )
    // } 

    // setUpFormElement(frmElem: any){
    // }  

    save() {
        console.log("this.dataEntryForm " + this.dataEntryForm)
        console.log('Saved: ' + JSON.stringify(this.dataEntryForm.value))
        this.dealService.postDealDetails(this.dataEntryForm.value);
    }

    updateForm(docId: string) {
        this.sectionForms = []
        this.loanforms = []
        this.loanforms.push(docId)
    }

    updatedSection(section: any) {
        this.sectionForms = []
        this.loanforms = []
        this.sectionForms.push(section)
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