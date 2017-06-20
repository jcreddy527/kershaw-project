import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import { DealService } from '../deal/deal.service'

@Component({
    selector: 'app-sectionform',
    templateUrl: './sectionform.component.html'
})
export class SectionFormComponent implements OnInit, OnDestroy {

    @Input('section')
    section: any

    ngOnInit(): void {
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.section.docId).subscribe(
            jsondata => {
                this.formElements = jsondata,
                    console.log("SectionFormComponent - : " +  JSON.stringify(this.section)),
                    this.FillFields();
                //    console.log("this.fields : "+JSON.stringify(this.formElements))
                error => console.log("Error: " + error)
            })

    }

    ngOnDestroy() {
        console.log("Section component destroying")
    }

    dataEntryForm: FormGroup
    formElements: any[] = []
    fields: any[] = []

    constructor(private fb: FormBuilder, private dealService: DealService) { }

    FillFields() {
        console.log(JSON.stringify(this.formElements))        
        this.formElements.forEach(data => {
            if(data.documentId == this.section.docId && data.sectionName == this.section.sectionId){
                this.fields.push(data.fields)
                data.fields.forEach(fld => {
                    this.dataEntryForm.addControl(fld.fieldName,new FormControl(fld.fieldValue))
                })                
            }
        })

        // for (var i = 0; i < this.formElements.length; i++) {
            
        //     if (this.formElements[i].sections[i].sectionName == this.section.docId) {
        //         this.fields.push(this.formElements[i].fields)
        //     }
        // }
        console.log("FillFields : " + JSON.stringify(this.fields))
    }

    save() {
        console.log(this.dataEntryForm.value)
        // this.dealService.postDealDetails(this.dataEntryForm.value);
    }
}