import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit, Input, OnDestroy, EventEmitter } from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
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

  httpUrl: string = '/api' + '/documentType/'

  constructor(fb: FormBuilder, private ds: DocumentService, private http: Http) {
    this.form = fb.group({
      "fieldName": new FormControl('', Validators.required),
      "dataType": new FormControl('select', Validators.required),
      "fieldPossibleValue": new FormControl('', Validators.required),
      "sectionName": new FormControl('Document Section 1', Validators.required)
    });
  }

  form: FormGroup;
  public selectdatatype: boolean = true;

  ngOnDestroy() {
    console.log("Field component Destroying...")
  }
  ngOnInit() {
    console.log("ng------" + JSON.stringify(this.selectedDocType));
  }

  dataTypeChange(event: any) {

    if (event.target.value == "Text") {
      this.selectdatatype = false;
      this.form.removeControl("fieldPossibleValue");
      return
    }
    this.selectdatatype = true;
    this.form.addControl("fieldPossibleValue", new FormControl("", Validators.required));

  }

  onSubmit() {
    this.form.addControl("documentTypeId", new FormControl(this.selectedDocType.documentTypeId))
    let body = JSON.stringify(this.form.value);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers, method: 'POST' });

    this.http.post(this.httpUrl+'field', body, options)
      .map(res => console.log(res.json))
      .subscribe((res) => {
        console.log("Done" + JSON.stringify(res))
        this.form.reset();
        this.ds.getDocumentType(this.selectedDocType.documentTypeId)
          .subscribe(jsondata => {
            console.log(JSON.stringify(jsondata))
            let fields: any[] = []
            jsondata.forEach(section => {
              section.fields.forEach(fld => {
                fields.push(fld)
              })
            })
            this.ds.fieldAdded.emit(fields)
            this.ds.hideFieldForm.emit(true)
          })
      });
  }

}

