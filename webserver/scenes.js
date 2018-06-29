/*global module */
/*global console */

let MAX_SCENES = 5; //0-5

const MAX_ROWS = 8;
const MAX_COLS = 8;

const MAX_LEDS = MAX_ROWS * MAX_COLS;

let client;

module.exports = {

    MAX_SCENES,

    _INIT: function (client1) {
        console.log("global init");
        client = client1;
    },

    _LOOP: function (scene) {

        loop(scene);

        client.writePixels();

    }
};




function getPixelNumber(row, col) {
    return row + (col * MAX_ROWS);
}

function setAllPixels(red, green, blue) {
    for (var row = 0; row < MAX_ROWS; row++) {
        for (var col = 0; col < MAX_COLS; col++) {
            setPixel1(row, col, red, green, blue);
        }
    }
}

function setPixel1(row, col, red, green, blue) {
    client.setPixel(getPixelNumber(row, col), red, green, blue);
}






function loop(scene) {
    switch (scene) {
        case 0:
            setAllPixels(255, 0, 0);
            break;

        case 1:
            scene_rainbow();
            break;

        default:
            break;
    }

}





var frequency = .3;
var changer = 0;

function scene_rainbow() {
    for (var led = 0; led < MAX_LEDS; led++) {

        var red = Math.sin(frequency * changer + 0) * 127 + 128;
        var green = Math.sin(frequency * changer + 2) * 127 + 128;
        var blue = Math.sin(frequency * changer + 4) * 127 + 128;

        changer += 0.01;

        setPixel1(led, red, green, blue);

    }
}
