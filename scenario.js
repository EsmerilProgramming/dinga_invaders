"use strict";

/**
 * JavaScript ES6 class Scenario.
 * @author Helio Frota
 */
class Scenario {

  constructor() {
    this.canvas = document.createElement("canvas");
    this.canvas.setAttribute("width", "600");
    this.canvas.setAttribute("height", "600");
    this.canvas.style.border = "1px solid black";
    this.canvas.style.background = "black";
    document.body.appendChild(this.canvas);
    this.ctx = this.canvas.getContext("2d");
    this.ctx.strokeStyle = "black";
  }

  drawStars() {
    let radius = 2;
    let diameter = radius * 2;
    this.ctx.fillStyle = "white";
    this.ctx.beginPath();
    this.ctx.arc(diameter + radius, radius, radius, 0, 2 * Math.PI);
    this.ctx.fill();
    let stars = [];
    for (let i = 0; i < 50; i++) {
      stars.push({
        c: Math.floor(3 * Math.random()),
        x: Math.floor(this.canvas.width * Math.random()),
        y: Math.floor(this.canvas.height * Math.random())
      });
    }

    for (let star of stars) {
      this.ctx.drawImage(this.canvas, star.c * diameter, 0, diameter,
                        diameter, star.x - radius, star.y - radius,
                        diameter, diameter);
    }
    this.ctx.closePath();
  }

  drawShield() {
    this.ctx.fillStyle = "blue";
    this.ctx.beginPath();
    this.ctx.rect(20, 580, 80, 10);
    this.ctx.fill();
    this.ctx.closePath();
    this.ctx.stroke();
  }

  loadAssets() {
    let img = new Image();
    let ctx = this.ctx;
    img.onload = function() {
      ctx.drawImage(img, 0, 0, 600, 600);
    };
    img.src = "images/universe.png";
  }

}
