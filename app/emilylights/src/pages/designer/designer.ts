import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';


@Component({
  templateUrl: 'SO_RainDrops.html'
})
export class SceneOptionRainDrops {
  constructor(params: NavParams) {

  }
}

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
  templateUrl: 'colorpicker.html'
})
export class ColorPickerPage {

  color: string = "#FF0000";

  constructor(params: NavParams) {

  }

  setColor(event: any) {
    this.color = event;
  }

  colorTouchStart() {

  }

  colorTouchEnd() {

  }

}

@Component({
  templateUrl: 'color-page.html'
})
export class ColorPage {

  colors: any[];

  constructor(public navCtrl: NavController, params: NavParams) {
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

  add() {
    this.navCtrl.push(ColorPickerPage, {});
  }

}

@Component({
  selector: 'page-designer',
  templateUrl: 'designer.html'
})
export class DesignerPage {

  private selected: string = null;

  constructor(public navCtrl: NavController) {

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
      if (this.selected == "0") {
        //Dots
        this.navCtrl.push(SceneOptionDots, {});
      }
      else if (this.selected == "1") {
        //Gradient
        this.navCtrl.push(SceneOptionGradient, {});
      }
      else if (this.selected == "2") {
        //Circles
        this.navCtrl.push(SceneOptionCircles, {});
      }
      else if (this.selected == "3") {
        //RainDrops
        this.navCtrl.push(SceneOptionRainDrops, {});
      }
    }
  }

  createScene() {

  }

  selectType(event: string) {
    this.selected = event;
  }

}
