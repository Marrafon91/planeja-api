import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  imports: [RouterOutlet],
  selector: 'app-template',
  styleUrl: './template.css',
  templateUrl: './template.html',
})
export class Template {
  activeItem = signal('dashboard');

  setActive(item: string) {
    this.activeItem.set(item);
  }
}
