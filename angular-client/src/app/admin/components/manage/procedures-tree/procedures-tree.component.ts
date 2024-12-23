import { SelectionModel } from '@angular/cdk/collections';
import { FlatTreeControl } from '@angular/cdk/tree';
import { Component, EventEmitter, Injectable, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { BehaviorSubject } from 'rxjs';
import { HospitalManagementService } from 'src/app/admin/services/hospital-management.service';

/**
 * Node for to-do item
 */
export class TodoItemNode {
  children!: TodoItemNode[] | null;
  item!: string;
  id!:number;
}

/** Flat to-do item node with expandable and level information */
export class TodoItemFlatNode {
  item!: string;
  id!: number | null;
  level!: number;
  expandable!: boolean;
}

/**
 * Checklist database, it can build a tree structured Json object.
 * Each node in Json object represents a to-do item or a category.
 * If a node is a category, it has children items and new items can be added under the category.
 */
@Injectable()
export class ChecklistDatabase {
  dataChange = new BehaviorSubject<TodoItemNode[]>([]);

  get data(): TodoItemNode[] {
    return this.dataChange.value;
  }

  constructor(private hospitalManagementService : HospitalManagementService) {
    this.initialize();
  }

  initialize() {
    this.hospitalManagementService.getSpecialitiesWithProcedures().subscribe(
      res =>{
        const data = this.buildFileTree(res);
        this.dataChange.next(data);
      },
      err =>{
        console.log(err)
      }
    )
  }

  buildFileTree(data : any) {
    const list : TodoItemNode[] = data.map((speciality : any) => {
      const node = new TodoItemNode();
      node.id = speciality.id
      node.item = speciality.name;
      node.children =  speciality.procedures.map((procedure : any) => {
        const childNode = new TodoItemNode();
        childNode.id = procedure.id;
        childNode.item = procedure.name;
        childNode.children = null;
        return childNode;
      });
      return node;
    });
    return list;
    
  }
}

@Component({
  selector: 'app-procedures-tree',
  templateUrl: './procedures-tree.component.html',
  styleUrls: ['./procedures-tree.component.css']
})
export class ProceduresTreeComponent implements OnChanges{
 /** Map from flat node to nested node. This helps us finding the nested node to be modified */
 flatNodeMap = new Map<TodoItemFlatNode, TodoItemNode>();

 /** Map from nested node to flattened node. This helps us to keep the same object for selection */
 nestedNodeMap = new Map<TodoItemNode, TodoItemFlatNode>();

 treeControl: FlatTreeControl<TodoItemFlatNode>;

 treeFlattener: MatTreeFlattener<TodoItemNode, TodoItemFlatNode>;

 dataSource: MatTreeFlatDataSource<TodoItemNode, TodoItemFlatNode>;

 @Input() checkedProceduresIds : number[] = []
 @Output() checkedProceduresIdsChange = new EventEmitter<number[]>();
 changesMade : boolean = false;

 /** The selection for checklist */
 checklistSelection = new SelectionModel<TodoItemFlatNode>(true /* multiple */);

 constructor(private _database: ChecklistDatabase, private hospitalManagementService : HospitalManagementService) {
   this.treeFlattener = new MatTreeFlattener(
     this.transformer,
     this.getLevel,
     this.isExpandable,
     this.getChildren,
   );
   this.treeControl = new FlatTreeControl<TodoItemFlatNode>(this.getLevel, this.isExpandable);
   this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

   this._database.dataChange.subscribe(data => {
     this.dataSource.data = data;
     this.checkHospitalAvailableProcedures();
   });
 }
  ngOnChanges(changes: SimpleChanges): void {
   if(!changes['checkedProceduresIds'].firstChange){
    this.checkHospitalAvailableProcedures()
   }
  }

 ngOnInit(): void {
 }

 getLevel = (node: TodoItemFlatNode) => node.level;

 isExpandable = (node: TodoItemFlatNode) => node.expandable;

 getChildren = (node: TodoItemNode): TodoItemNode[] | null => node.children;

 hasChild = (_: number, _nodeData: TodoItemFlatNode) => _nodeData.expandable;

 hasNoContent = (_: number, _nodeData: TodoItemFlatNode) => _nodeData.item === '';

 /**
  * Transformer to convert nested node to flat node. Record the nodes in maps for later use.
  */
 transformer = (node: TodoItemNode, level: number) => {
   const existingNode = this.nestedNodeMap.get(node);
   const flatNode =
     existingNode && existingNode.item === node.item ? existingNode : new TodoItemFlatNode();
   flatNode.id = node.children === null? node.id : null;
   flatNode.item = node.item;
   flatNode.level = level;
   flatNode.expandable = !!node.children?.length;
   this.flatNodeMap.set(flatNode, node);
   this.nestedNodeMap.set(node, flatNode);
   return flatNode;
 };

 /** Whether all the descendants of the node are selected. */
 descendantsAllSelected(node: TodoItemFlatNode): boolean {
   const descendants = this.treeControl.getDescendants(node);
   const descAllSelected =
     descendants.length > 0 &&
     descendants.every(child => {
       return this.checklistSelection.isSelected(child);
     });
   return descAllSelected;
 }

 /** Whether part of the descendants are selected */
 descendantsPartiallySelected(node: TodoItemFlatNode): boolean {
   const descendants = this.treeControl.getDescendants(node);
   const result = descendants.some(child => this.checklistSelection.isSelected(child));
   return result && !this.descendantsAllSelected(node);
 }

 /** Toggle the to-do item selection. Select/deselect all the descendants node */
 todoItemSelectionToggle(node: TodoItemFlatNode): void {
   this.checklistSelection.toggle(node);
   const descendants = this.treeControl.getDescendants(node);
   this.checklistSelection.isSelected(node)
     ? this.checklistSelection.select(...descendants)
     : this.checklistSelection.deselect(...descendants);

   // Force update for the parent
   descendants.forEach(child => this.checklistSelection.isSelected(child));
   this.checkAllParentsSelection(node);
 }

 /** Toggle a leaf to-do item selection. Check all the parents to see if they changed */
 todoLeafItemSelectionToggle(node: TodoItemFlatNode): void {
   this.checklistSelection.toggle(node);
   this.checkAllParentsSelection(node);
 }

 /* Checks all the parents when a leaf node is selected/unselected */
 checkAllParentsSelection(node: TodoItemFlatNode): void {
   let parent: TodoItemFlatNode | null = this.getParentNode(node);
   while (parent) {
     this.checkRootNodeSelection(parent);
     parent = this.getParentNode(parent);
   }
 }

 /** Check root node checked state and change it accordingly */
 checkRootNodeSelection(node: TodoItemFlatNode): void {
   const nodeSelected = this.checklistSelection.isSelected(node);
   const descendants = this.treeControl.getDescendants(node);
   const descAllSelected =
     descendants.length > 0 &&
     descendants.every(child => {
       return this.checklistSelection.isSelected(child);
     });
   if (nodeSelected && !descAllSelected) {
     this.checklistSelection.deselect(node);
   } else if (!nodeSelected && descAllSelected) {
     this.checklistSelection.select(node);
   }
 }

 /* Get the parent node of a node */
 getParentNode(node: TodoItemFlatNode): TodoItemFlatNode | null {
   const currentLevel = this.getLevel(node);

   if (currentLevel < 1) {
     return null;
   }

   const startIndex = this.treeControl.dataNodes.indexOf(node) - 1;

   for (let i = startIndex; i >= 0; i--) {
     const currentNode = this.treeControl.dataNodes[i];

     if (this.getLevel(currentNode) < currentLevel) {
       return currentNode;
     }
   }
   return null;
 }

 checkHospitalAvailableProcedures(){
  const nodes = this.treeControl.dataNodes.filter(el => this.checkedProceduresIds.includes(el.id!))
  if(nodes.length > 0){
    nodes.forEach(node => {
      this.checklistSelection.select(node);
      this.checkAllParentsSelection(node);
    });
    // this.checklistSelection.deselect();
    // this.checklistSelection.select(...nodes)
    // nodes.forEach(node => {
    //   const parent = this.getParentNode(node);
    //   this.checklistSelection.select(parent!);
    // });
  }
 }

 undoChanges(){
  this.changesMade = false;
  this.checklistSelection.deselect(...this.treeControl.dataNodes)
  this.checkHospitalAvailableProcedures();
 }

 changesHasBeenMade(){
  this.changesMade = true;
 }

 saveProcedureConfiguration(){
   this.checkedProceduresIds = this.treeControl.dataNodes.filter(el => this.checklistSelection.isSelected(el) && el.id != null).map(el =>el.id!);   
   this.checkedProceduresIdsChange.emit(this.checkedProceduresIds)
 }
}
