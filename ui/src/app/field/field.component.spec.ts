import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Component, OnInit, Input, OnDestroy, EventEmitter } from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import { FieldComponent } from './field.component';

describe('FieldComponent', () => {
  let component: FieldComponent;
  let fixture: ComponentFixture<FieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [FieldComponent],
      providers: [{ provide: Http }, { provide: DocumentService, useClass: DocumentService }],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created field component ', () => {
    expect(component).toBeDefined();
  });

  it('should be created with default field name', () => {
    expect(component.form.controls['fieldName'].value).toBe('');
  });

  it('should be created with default datatype ', () => {
    expect(component.form.controls['dataType'].value).toBe('Radio');
  });

  it('should be created with default section name ', () => {
    expect(component.form.controls['sectionName'].value).toBe('Document Section 1');
  });
});
