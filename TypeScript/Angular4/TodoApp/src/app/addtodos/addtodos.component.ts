import { Component, OnInit } from '@angular/core';
import { Todo } from '../todoTemplate' ;
import { TododataService } from '../tododata.service';

@Component({
  selector: 'app-addtodos',
  templateUrl: './addtodos.component.html',
  styleUrls: ['./addtodos.component.css']
})

export class AddtodosComponent implements OnInit {

  todo: String = '' ;
  desc: String = '' ;
  counter = 0 ;
  date: Date ;
  todoService: TododataService ;
  constructor(service: TododataService) {
    this.todoService = service ;
  }
  ngOnInit() {
  }

  onSubmit() {
    console.log(`${this.todo} and ${this.desc} submitted`) ;
    this.todoService.addTodo( new Todo(this.counter++, this.todo, this.desc, this.date) ) ;
    this.todo = this.desc = '' ;
    this.date = null ;
  }



}