import { Component, Input, OnInit} from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import { DealService } from '../deal/deal.service'

@Component({
    selector: 'app-loanform',
    templateUrl: './fullfieldsform.component.html'
})
export class FullFieldFormComponent implements OnInit{

    dataEntryForm: FormGroup
    sections: any[] = []
    fields: any[] = []
    formElements: any[] = []
    
    @Input('documentID')
    documentID: number

    constructor(private fb: FormBuilder, private dealService: DealService) { }

    ngOnInit(): void {
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.documentID).subscribe(
            jsondata => {
                this.formElements = jsondata
                this.fillFields(jsondata);
                error => console.log("Error: " + error)
            })
    }

    fillFields(data: any) {
        console.log("Data : " + JSON.stringify(data))
        data.forEach(item => {
            if (item.fields.length > 0) {
                let section: any = {}
                section.name = item.sectionName
                section.fields = item.fields
                this.sections.push(section)
                item.fields.forEach(fld => {
                    this.dataEntryForm.addControl(fld.fieldId, new FormControl(fld.fieldValue))
                })
            }
        })

    }

    save() {
        this.formElements.forEach(frmElm => {
            frmElm.fields.forEach(fld => {
                fld.fieldValue = this.dataEntryForm.value[fld.fieldId]
            })
        })
        this.dealService.saveFields(this.formElements);
    }
}