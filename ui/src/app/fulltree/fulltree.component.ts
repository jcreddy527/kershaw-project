import { Component, Input , OnInit} from '@angular/core';
import { TreeNode, TREE_ACTIONS, KEYS, IActionMapping } from 'angular-tree-component';
import { FullTreeService } from './fulltree.service'
import { Observable } from 'rxjs/Observable' 


@Component({
  selector: 'app-fulltree',
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
export class FullTreeComponent  implements OnInit{
 nodes:any[]=[];
 nodes2:any[]=[];

 constructor(private fullService: FullTreeService) {
    }
  ngOnInit() {
       this.fullService.getFileNames().
            subscribe(
            (jsondata) => {
                // console.log("Data received: "+JSON.stringify(jsondata))  
                 this.nodes = jsondata  
                 this.doSomething(this.nodes);
             },
             error => console.log("Error: " + error)
            )  

      
  }
doSomething(nodes: any[]){   
        let myObservable = new Observable<any[]>(observer => {
            let nodesTmp: any[] = []
            nodes.forEach(data => {
              nodesTmp.push({
                    expanded: true,
                    name: data.documentType,
                    subTitle: 'Tree Root',
                    children: [
                        {
                        name: 'child1',
                        subTitle: 'Section 1',
                        hasChildren: false
                        }, {
                        name: 'child2',
                        subTitle: 'Section 2',
                        hasChildren: false
                        }
                    ]
              })
              observer.next(nodesTmp);
              if(nodes.length==nodesTmp.length){
                  observer.complete();
              }
            })            
        })
        let subscription = myObservable.subscribe((nodedata: any[])=> {
            this.nodes2 = nodedata
        })         
    }
    
}