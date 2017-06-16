import { Injectable, EventEmitter } from '@angular/core'
import { Http,Headers,RequestOptions,Response } from '@angular/http'
import {Observable } from 'rxjs/Observable'

@Injectable()
export class SharedService{
    obj : Object     
    constructor(private _http: Http){   }
    documentChanged = new EventEmitter<any>()

    docChanged(){
        this.documentChanged.emit()
    }

    getObjectFromTree(obj, id){
        console.log(" SharedService - getObjectFromTree OBJ :"+obj)
        console.log(" SharedService - getObjectFromTree ID :"+id)
        this.obj=obj;
    }

    getTreeObject(){
        console.log(" SharedService - getTreeObject OBJ :"+this.obj)
        return this.obj;
    }


    getDocumentObject(id){

        console.log("getDocumentObject")
                
    }

    private handleError(error: Response){
        // console.error(error)
        return Observable.throw(error.json().error || 'Server Error')
    }

}


