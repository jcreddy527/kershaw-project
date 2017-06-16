import { Injectable, EventEmitter } from '@angular/core';
import { Headers, Http,Response } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'

@Injectable()
export class DocumentService {

    childUpdated = new EventEmitter<any>()
 documentType = new EventEmitter<any>()
 private httpUrl = 'http://localhost:8080/documentType/getAll';  // URL to web api
 
constructor(private http: Http) {         
}
 
 getAllDocumentTypes():Observable<any[]>{return this.http.get(this.httpUrl).
            map((response: Response) =>
                    <any[]>response.json()                
            ).
            do(data => console.log('ALL '+JSON.stringify(data)))
            .catch(this.handleError)      
    }

 setDocumentType(docType: any){
         console.log("Data aarrived: "+JSON.stringify(docType))
         this.documentType.emit(docType);
 }  
 
 private handleError(error: Response){
        // console.error(error)
        return Observable.throw(error.json().error || 'Server Error')
      // return null;
    }

     updateChild(){
        this.childUpdated.emit("Done!")
    }

}
