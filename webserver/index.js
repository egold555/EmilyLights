var OPC = require('./opc')
var client = new OPC('192.168.1.122', 7890);


var frequency = .3;
var changer = 0;

function draw() {

    for (var pixel = 0; pixel < 64; pixel++) {
        var red = Math.sin(frequency * changer + 0) * 127 + 128;
        var green = Math.sin(frequency * changer + 2) * 127 + 128;
        var blue = Math.sin(frequency * changer + 4) * 127 + 128;

        changer += 1;

        client.setPixel(pixel, red, green, blue);
    }
    client.writePixels();
}

setInterval(draw, 500);
