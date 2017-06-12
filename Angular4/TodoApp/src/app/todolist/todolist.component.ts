import { TododataService } from './../tododata.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent implements OnInit {

  todoService: TododataService ;
  constructor(service: TododataService) {
    this.todoService = service ;
   }

  ngOnInit() {
  }

  todoRemove(counter: number) {
    console.log('Finishing TODO') ;
  }

}