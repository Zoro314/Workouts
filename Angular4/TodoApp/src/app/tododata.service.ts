import { Todo } from './todoTemplate';
import { Injectable } from '@angular/core';

@Injectable()
export class TododataService {

todos: Todo[] = [] ;

constructor() { }

addTodo(todo: Todo) {
    this.todos.push( todo ) ;
    console.log(`Todo added >> ${JSON.stringify(todo)}`) ;
    console.log(`Todo returned ${this.todos[this.todos.length - 1].title}`) ;
}

getTodos() {
   // console.log(`Todo returned ${this.todos[0].title}`) ;
   return this.todos ;
}


}
