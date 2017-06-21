import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl } from '@angular/forms';
import { DealService } from '../deal/deal.service'

@Component({
    selector: 'app-sectionform',
    templateUrl: './sectionform.component.html'
})
export class SectionFormComponent implements OnInit {
    pagesTitle :string
    dataEntryForm: FormGroup
    formElements: any[] = []
    fields: any[] = []
    docsID: number
    secID: number
    secName: string

    @Input('section')
    section: any

    constructor(private fb: FormBuilder, private dealService: DealService) { }

    ngOnInit(): void {
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.section.docId).subscribe(
            jsondata => {
                this.formElements = jsondata,
                    this.FillFields();
                error => console.log("Error: " + error)
            })

    }

    FillFields() {
        this.formElements.forEach(data => {
            if (data.sectionName == this.section.sectionId) {
                this.docsID = data.documentTypeId
                this.secID = data.sectionId
                this.secName = data.sectionName
                this.fields.push(data.fields)
                data.fields.forEach(fld => {
                    this.dataEntryForm.addControl(fld.fieldId, new FormControl(fld.fieldValue))
                })
            }
        })
    }

    save() {
        let sections: any[] = []
        let section: any = {}
        section.documentTypeId = this.docsID
        section.sectionId = this.secID
        section.sectionName = this.secName
        let fields: any[] = []
        this.fields.forEach(fieldsArry => {
            fieldsArry.forEach(fld => {
                let field: any = {}
                field.fieldId = fld.fieldId
                field.fieldValue = this.dataEntryForm.value[fld.fieldId]
                fields.push(field)
            })
        })
        section.fields = fields
        sections.push(section)
        console.log(JSON.stringify(sections))
        this.dealService.saveFields(sections)
        this.pagesTitle = "Fields updated";
    }
}