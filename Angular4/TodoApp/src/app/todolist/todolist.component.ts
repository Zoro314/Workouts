import { Todo } from './../todoTemplate';
import { TododataService } from './../tododata.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent implements OnInit {

  todoService: TododataService ;
  @Input() todoCounter: string;
  todoDeleteCounter: number ;

  constructor(service: TododataService) {
    this.todoService = service ;
  }

  ngOnInit() {
  }

  todoRemove(finishedToDo: Todo ) {
    console.log('Finishing TODO for counter >> ' + finishedToDo.counter ) ;
    this.todoService.removeTodo( finishedToDo ) ;
  }

}


