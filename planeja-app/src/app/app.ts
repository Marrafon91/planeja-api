import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Template } from './template/template'
@Component({
  imports: [RouterOutlet, Template ],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('planeja-app');
}
