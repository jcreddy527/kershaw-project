import { inject, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { Injectable, EventEmitter } from '@angular/core';
import { MockBackend } from '@angular/http/testing';
import {  Http, ConnectionBackend, BaseRequestOptions, Response, ResponseOptions, Headers } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import { DocumentService } from './document.service';

describe('DocumentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
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
    });
  });

  it('should be created', inject([DocumentService], (service: DocumentService) => {
    expect(service).toBeTruthy();
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
        res = response;
      });
      tick();
      expect(res["username"]).toBe("John Elway");
    }))
  );
});
