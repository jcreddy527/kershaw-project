import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { TreeNode, TREE_ACTIONS, KEYS, IActionMapping } from 'angular-tree-component';
import { FullTreeService } from './fulltree.service'
import { SharedService } from '../services/sharedService.service'
import { Router,ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable'


@Component({
    selector: `app-fulltree`,
    styles: [
        `button: {
        line - height: 24px;
        box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.5);
        border: none;
        border-radius: 2px;
        background: #A3D9F5;
        cursor: pointer;
        margin: 0 3px;
      }`
    ],
    templateUrl: './fulltree.component.html'
})


export class FullTreeComponent implements OnInit {
    nodes: any[] = [];
    nodes2: any[] = [];
    id:number;
    fields: any[] = [];

    constructor(private fullService: FullTreeService, private sharedService : SharedService,private router: Router) {
    }

    @Output()
    docSelected = new EventEmitter<string>()    

    @Output()
    sectionSelected = new EventEmitter<any>()

    ngOnInit() {
        this.fullService.getFileNames().
            subscribe(
            (jsondata) => {
                // console.log("Data received: "+JSON.stringify(jsondata))  
                this.nodes = jsondata
                this.showTree(this.nodes);
            },
            error => console.log("Error: " + error)
            )

    }

    showTree(nodes: any[]) {
        let myObservable = new Observable<any[]>(observer => {
            let nodesTmp: any[] = []
           // console.log(" doSomething : "+JSON.stringify(this.nodes))
        //    for(var i=0;i<nodes.length;i++){
        //        this.fields=nodes[i].fields
        //    }
           console.log("FIELDS : "+JSON.stringify(nodes))
            nodes.forEach(data => {
                let sections: string[] = []
                data.sections.forEach(item => {
                    let tmp: any = {}
                    tmp.docId = data.documentTypeId
                    tmp.name = item.sectionName
                    tmp.hasChildren = true                    
                    sections.push(tmp)
                })

                nodesTmp.push({
                    expanded: true,
                    name: data.documentType,
                    id: data.documentTypeId,
                    subTitle: data.documentType,
                    children: sections
                })
                observer.next(nodesTmp);
                if (nodes.length == nodesTmp.length) {
                    observer.complete();
                }
          
            })
        })
        let subscription = myObservable.subscribe((nodedata: any[]) => {
            this.nodes2 = nodedata
        })
    }

    getDocument(obj, id) {
        if(obj.children){
             console.log("Root clicked!!")
             this.docSelected.emit(id)
        }else{
             console.log("Child clicked!! "+obj)
             let section: any = {}
             section.docId = obj.docId
             section.sectionId = obj.name
             this.sectionSelected.emit(section)
             console.log("myfunction OBJ :" + JSON.stringify(section))     
        }
        console.log("myfunction OBJ :" + JSON.stringify(obj))        
        // this.docSelected.emit(id)
    }
}




//  console.log("myfunction ID :" + JSON.stringify(id))
        //    this.sharedService.getDocumentObject(id);
        //  console.log("OBJ ID :" + JSON.stringify(obj))
        // const dealComponent = id
        // console.log("rot "+id)
        // this.router.navigate(['dealSections/'+id]);