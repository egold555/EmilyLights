import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';


@Component({
  templateUrl: 'SO_Circles.html'
})
export class SceneOptionCircles {
  constructor(params: NavParams) {

  }
}

@Component({
  templateUrl: 'SO_Dots.html'
})
export class SceneOptionDots {
  constructor(params: NavParams) {

  }
}

@Component({
  templateUrl: 'SO_Gradient.html'
})
export class SceneOptionGradient {
  constructor(params: NavParams) {

  }
}

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
  private selected: string = null;

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

  openOptions() {
    if (this.selected != null) {
      if (this.selected == 0) {
        //Dots
          this.navCtrl.push(SceneOptionDots, {});
      }
      else if (this.selected == 1) {
        //Gradient
          this.navCtrl.push(SceneOptionGradient, {});
      }
      else if (this.selected == 2) {
        //Circles
          this.navCtrl.push(SceneOptionCircles, {});
      }
    }
  }

  createScene() {

  }

  selectType(event: string) {
    this.selected = event;
  }

}
