import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http } from '@angular/http';


@Component({
  templateUrl: 'SO_RainDrops.html'
})
export class SceneOptionRainDrops {
  constructor(params: NavParams) {
    this.dropLength = DesignerPage.options.dropLength;
    this.dropValueStart = DesignerPage.options.dropValueStart;
    this.dropMinTime = DesignerPage.options.dropMinTime;
    this.dropMaxTime = DesignerPage.options.dropMaxTime;
    this.speed = DesignerPage.options.speed;
  }

  ionViewCanLeave() {
    DesignerPage.options = { dropLength: this.dropLength, dropValueStart: this.dropValueStart, dropMinTime: this.dropMinTime, dropMaxTime: this.dropMaxTime, speed: this.speed };
  }

}

@Component({
  templateUrl: 'SO_Circles.html'
})
export class SceneOptionCircles {
  constructor(params: NavParams) {
    this.width = DesignerPage.options.width;
    this.speed = DesignerPage.options.speed;
    this.dropMinTime = DesignerPage.options.dropMinTime;
    this.dropMaxTime = DesignerPage.options.dropMaxTime;
  }
  ionViewCanLeave() {
    DesignerPage.options = { width: this.width, speed: this.speed, dropMinTime: this.dropMinTime, dropMaxTime: this.dropMaxTime };
  }
}

@Component({
  templateUrl: 'SO_Dots.html'
})
export class SceneOptionDots {
  constructor(params: NavParams) {
    this.advance = DesignerPage.options.advance;
    this.colorAdvance = DesignerPage.options.colorAdvance;
  }
  ionViewCanLeave() {
    DesignerPage.options = { advance: this.advance, colorAdvance: this.colorAdvance };
  }
}

@Component({
  templateUrl: 'SO_Gradient.html'
})
export class SceneOptionGradient {
  constructor(params: NavParams) {
    this.direction = DesignerPage.options.direction;
    this.speed = DesignerPage.options.speed;
    this.smoothness = DesignerPage.options.smoothness;
  }

  ionViewCanLeave() {
    DesignerPage.options = { direction: this.direction, speed: this.speed, smoothness: this.smoothness };
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

  ionViewCanLeave() {
    var thisIsDumb = { hex: this.color };
    ColorPage.colors.push(thisIsDumb);
  }

}

@Component({
  templateUrl: 'color-page.html'
})
export class ColorPage {

  public static colors: any[] = [];

  constructor(public navCtrl: NavController, params: NavParams) {
    ColorPage.colors = DesignerPage.colors;
    if (ColorPage.colors == null) {
      ColorPage.colors = [];
    }
  }

  get colors() {
    return ColorPage.colors;
  }

  add() {
    this.navCtrl.push(ColorPickerPage, {});
  }

  ionViewCanLeave() {
    DesignerPage.colors = ColorPage.colors;
  }

}

@Component({
  selector: 'page-designer',
  templateUrl: 'designer.html'
})
export class DesignerPage {

  public static options: any = {};
  public static colors: any = [];

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
    var postData = this.getPostDataForScenePreviewAndCeate();
    this.sendPost("add", JSON.stringify(postData));
  }

  previewScene() {
    var postData = this.getPostDataForScenePreviewAndCeate();
    this.sendPost("preview", JSON.stringify(postData));
  }

  getPostDataForScenePreviewAndCeate() {
    var postData: any = {};
    postData.name = this.value_name; //this works cause Ionic. TS hates this!
    postData.type = this.type;
    postData.options = DesignerPage.options;
    postData.colors = DesignerPage.colors; //not exactly correct butseems to work for the most part
    return postData;
  }

  getURL(file: string) {
    return "http://192.168.1.56:8000/" + file;
  }

  sendPost(prefix: string, data: string) {
    this.http.post(this.getURL(prefix), data).subscribe();
  }

}
