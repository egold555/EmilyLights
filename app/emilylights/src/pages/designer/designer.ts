import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http } from '@angular/http';


@Component({
  templateUrl: 'SO_RainDrops.html'
})
export class SceneOptionRainDrops {
  constructor(params: NavParams) {

  }

  ionViewCanLeave() {
    DesignerPage.options = { dropLength: this.dropLength, dropValueStart: this.dropValueStart, dropMinTime: this.dropMinTime, dropMaxTime: this.dropMaxTime, speed: this.speed};
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

  public static options: any = {};

  constructor(public navCtrl: NavController, public http: Http) {

  }

  colorTouchStart() {

  }

  colorTouchEnd() {

  }

  openColorPage() {
    this.navCtrl.push(ColorPage, {});
  }

  openOptions() {
    var selected: string = this.type;
    if (selected != null) {
      if (selected == "0") {
        //Dots
        this.navCtrl.push(SceneOptionDots, {});
      }
      else if (selected == "1") {
        //Gradient
        this.navCtrl.push(SceneOptionGradient, {});
      }
      else if (selected == "2") {
        //Circles
        this.navCtrl.push(SceneOptionCircles, {});
      }
      else if (selected == "3") {
        //RainDrops
        this.navCtrl.push(SceneOptionRainDrops, {});
      }
    }
  }

  createScene() {
    var postData: any = {};
    postData.name = this.value_name; //this works cuse Ionic. TS hates this!
    postData.type = this.type;
    postData.options = DesignerPage.options;
    this.sendPost("add", JSON.stringify(postData));
  }

  getURL(file: string) {
    return "http://192.168.1.56:8000/" + file;
  }

  sendPost(prefix: string, data: string) {
    this.http.post(this.getURL(prefix), data).subscribe();
  }

}
