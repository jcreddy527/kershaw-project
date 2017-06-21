import { Injectable, EventEmitter } from '@angular/core';
import { Headers, Http,Response } from '@angular/http';
import { Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'

@Injectable()
export class DocumentService {

 childUpdated = new EventEmitter<any>()

 public fieldAdded = new EventEmitter<any>();
 public hideFieldForm = new EventEmitter<boolean>()

 private httpUrl = 'api'+'/documentType/shallow';  // URL to web api
 
constructor(private http: Http) {         
}
 
 getAllDocumentTypes():Observable<any[]>{
         return this.http.get(this.httpUrl).
            map((response: Response) =>
                    <any[]>response.json()                
            ).
            do(data => console.log('ALL '+JSON.stringify(data)))
            .catch(this.handleError)      
    }

getDocumentType(docId:String):Observable<any[]>{
         return this.http.get('api'+'/documentType/'+docId+'/sections').
            map((response: Response) =>
                    <any[]>response.json()                
            ).
            do(data => console.log('Done!'))
            .catch(this.handleError)      
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
