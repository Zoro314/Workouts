import { TododataService } from './tododata.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [TododataService]
})

export class AppComponent {
  title = 'TODO LIST';
}
