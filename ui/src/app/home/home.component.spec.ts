import { async, ComponentFixture, inject, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule} from '@angular/forms';
import { Component, OnInit, Input} from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Headers, Http, Response, RequestOptions, BaseRequestOptions, ConnectionBackend, ResponseOptions} from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import { MockBackend } from '@angular/http/testing';
import { HomeComponent } from './home.component';
import { FieldComponent } from '../field/field.component';

describe('HomeComponent', () => {
    let component: HomeComponent;
    let fixture: ComponentFixture<HomeComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [ReactiveFormsModule],
            declarations: [HomeComponent, FieldComponent],
            providers: [
                {
                    provide: Http, useFactory: (backend: ConnectionBackend, defaultOptions: BaseRequestOptions) => {
                        return new Http(backend, defaultOptions);
                    }, deps: [MockBackend, BaseRequestOptions]
                },
                { provide: MockBackend, useClass: MockBackend },
                { provide: BaseRequestOptions, useClass: BaseRequestOptions },
                { provide: DocumentService, useClass: DocumentService }
            ]
        })
        fixture = TestBed.createComponent(HomeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should be created', inject([DocumentService], (service: DocumentService) => {
        expect(component).toBeDefined;
    }));

    it('should retrieve document type list',
        inject([DocumentService, MockBackend], fakeAsync((documentService: DocumentService, mockBackend: MockBackend) => {
            let res: Object;
            mockBackend.connections.subscribe(c => {
                expect(c.request.url).toBe('api' + '/documentType/shallow');
                let output = new ResponseOptions({ body: '{"username": "John Elway"}' });
                c.mockRespond(new Response(output));
            });
            documentService.getAllDocumentTypes().subscribe((response) => {
                component.docTypeList = response
                res = response;
            });
            tick();

            expect(component.docTypeList.username).toBeDefined();
            expect(component.docTypeList.username).toContain("John Elway");
        }))
    );
});
