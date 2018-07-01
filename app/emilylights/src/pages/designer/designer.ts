import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';


@Component({
  templateUrl: 'color-page.html'
})
export class ColorPage {

  constructor(params: NavParams) {

  }
}

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
    this.navCtrl.push(ColorPage, {});
  }

}
