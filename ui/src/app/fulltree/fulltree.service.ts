import { Injectable } from '@angular/core'
import { Http,Headers,RequestOptions,Response } from '@angular/http'
import {Observable } from 'rxjs/Observable'
import 'rxjs/Rx'

@Injectable()
export class FullTreeService{
    httpUrl: string = '/api' + '/documentType/getDocumentTypes'

    constructor(private _http: Http){   }

    getFileNames():Observable<any[]>{
         return this._http.
            get(this.httpUrl).
            map((response: Response) =>
                    response.json()                
            ).
             //do(data => console.log('JSON: '+JSON.stringify(data))).
            catch(this.handleError)      
    }

    private handleError(error: Response){
        // console.error(error)
        return Observable.throw(error.json().error || 'Server Error')
    }

}