import { Injectable, EventEmitter } from '@angular/core'
import { Http,Headers,RequestOptions,Response } from '@angular/http'
import {Observable } from 'rxjs/Observable'
import 'rxjs/Rx'

@Injectable()
export class DealService{
    httpUrl: string = ''
    childUpdated = new EventEmitter<any>()

    constructor(private _http: Http){   }

    getDealFields():Observable<any[]>{
        return this._http.
            get(this.httpUrl).
            map((response: Response) =>
                    <any[]>response.json()                
            ).
            // do(data => console.log('JSON: '+JSON.stringify(data))).
            catch(this.handleError)      
    }

    private handleError(error: Response){
        // console.error(error)
        return Observable.throw(error.json().error || 'Server Error')
    }

        postDealDetails(value : any){
        let body = JSON.stringify(value)
        let headers = new Headers({'content-Type':'application/json'})
        let options = new RequestOptions({headers:headers})
        console.log("body : "+body+" headers :"+headers+" options :"+options)
        //return this._http.post('http://localhost:3100/postemployee',body,options).map(this.extractData).catch(this.handleError)
    }
       private extractData(res:Response){
        let body = res.json()
        return body.fields || {}
    }

    updateChild(){
        this.childUpdated.emit("Done!")
    }

}