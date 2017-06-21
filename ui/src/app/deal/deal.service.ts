import { Injectable, EventEmitter } from '@angular/core'
import { Http, Headers, RequestOptions, Response } from '@angular/http'

import { Observable } from 'rxjs/Observable'
import 'rxjs/Rx'


@Injectable()
export class DealService {

    childUpdated = new EventEmitter<any>()

   httpUrl: string = '/api' + '/documentType/'
    constructor(private _http: Http) {}

    getDealFields(id): Observable<any[]> {
        console.log("getDealFields  : DealService " + id)
        return this._http.
            get(this.httpUrl + id +"/sections").
            map((response: Response) =>
                <any[]>response.json()
            ).
            catch(this.handleError)
    }


    private handleError(error: Response) {
        return Observable.throw(error.json().error || 'Server Error')
    }

 private extractData(res: Response) {
        let body = res.json()
        return body.fields || {}
    }
    updateChild() {
        this.childUpdated.emit("Done!")
    }

     saveFields(body : any){                       
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers, method : 'POST'});
        this._http.post(this.httpUrl+'fields/values', JSON.stringify(body), options)
        .map(res => console.log(res.json))
        .subscribe((res) => console.log("Done")); 
    }

}