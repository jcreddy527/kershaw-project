import { Component, Input, OnInit} from '@angular/core'
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

    constructor(private fb: FormBuilder, private dealService: DealService, private route: ActivatedRoute) {    }

    ngOnInit(): void {
        this.routedId = this.route.snapshot.params['dealComponent'];
        console.log("routed Id " + this.routedId)
        this.dataEntryForm = this.fb.group({})
        this.dealService.getDealFields(this.routedId).
            subscribe(
            jsondata => {
                this.formElements = jsondata,
                    this.FillFields();
                    error => console.log("Error: " + error)
             })
    }

    FillFields() {
        for (var i = 0; i < this.formElements.length; i++) {
            this.fields.push(this.formElements[i].fields)
        }
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

  