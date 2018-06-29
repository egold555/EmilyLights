import { Component, ViewChild, Renderer } from '@angular/core';
import { Platform } from 'ionic-angular';

@Component({
  selector: 'canvas-draw',
  templateUrl: 'canvas-draw.html'
})
export class CanvasDraw {

  @ViewChild('myCanvas') canvas: any;
  canvasElement: any;

  lastX: number;
  lastY: number;

  constructor(public platform: Platform, public renderer: Renderer) {
    console.log('Hello CanvasDraw Component');
  }

  ngAfterViewInit() {
    this.canvasElement = this.canvas.nativeElement;

    this.renderer.setElementAttribute(this.canvasElement, 'width', this.platform.width() + ''); //convert it to a string for some reason
    this.renderer.setElementAttribute(this.canvasElement, 'height', this.platform.height() + '');

  }

  handleStart(ev) {
    //console.log(ev);


    this.lastX = ev.touches[0].pageX;
    this.lastY = ev.touches[0].pageY;

  }

  handleMove(ev) {
    //console.log(ev);

    let ctx = this.canvasElement.getContext('2d');

    let currentX = ev.touches[0].pageX;
    let currentY = ev.touches[0].pageY;

    ctx.beginPath();
    ctx.lineJoin = "round";
    ctx.moveTo(this.lastX, this.lastY);
    ctx.lineTo(currentX, currentY);
    ctx.closePath();
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 5;
    ctx.stroke();

    //Update after draw
    this.lastX = currentX;
    this.lastY = currentY;

  }

  handleEnd(ev) {
    //console.log(ev);
  }

}
