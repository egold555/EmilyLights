import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';


@Component({
  templateUrl: 'color-page.html'
})
export class ColorPage {

  colors: any[];

  constructor(params: NavParams) {
    this.colors = [

      {
        name: 'Test',
        hex: '#a0d831',
      },
      {
        name: 'Test #2',
        hex: '#fc52db',
      }

    ];
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
