import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-designer',
  templateUrl: 'designer.html'
})
export class DesignerPage {

  private color: string = "FF0000";

  constructor(public navCtrl: NavController) {

  }

  setColor(ev: string) {
    this.color = ev;
  }

  colorTouchStart() {

  }

  colorTouchEnd() {

  }

  openColorPage() {

  }

}
