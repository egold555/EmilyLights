/*global module */
/*global console */

let MAX_SCENES = 5; //0-5

const MAX_ROWS = 8;
const MAX_COLS = 8;

const MAX_LEDS = MAX_ROWS * MAX_COLS;

let socket;

module.exports = {

    MAX_SCENES,

    _INIT: function (socket) {
        console.log("global init");
        this.socket = socket;
    },

    _LOOP: function (scene) {
        //console.log("loop " + scene);

        try {

            var packet = new Uint8ClampedArray(4 + MAX_LEDS * 3);
            if (this.socket.readyState != 1 /* OPEN */ ) {
                // The server connection isn't open. Nothing to do.
                return;
            }

            if (this.socket.bufferedAmount > packet.length) {
                // The network is lagging, and we still haven't sent the previous frame.
                // Don't flood the network, it will just make us laggy.
                // If fcserver is running on the same computer, it should always be able
                // to keep up with the frames we send, so we shouldn't reach this point.
                return;
            }

            loop(scene, packet);
            //console.log("Sent packet: " + new Date().valueOf());
            this.socket.send(packet.buffer);

        } catch (ex) {
            console.log(ex);
        }

    }
};

function loop(scene, packet) {

    switch (scene) {
        case 0:
            setAllPixels(packet, 255, 0, 0);
            break;

        case 1:
            scene_rainbow(packet);
            break;

        default:
            break;
    }

}

var frequency = .3;
var changer = 0;

function scene_rainbow(packet) {
    for (var led = 0; led < MAX_LEDS; led++) {

        var red = Math.sin(frequency * changer + 0) * 127 + 128;
        var green = Math.sin(frequency * changer + 2) * 127 + 128;
        var blue = Math.sin(frequency * changer + 4) * 127 + 128;

        changer += 0.01;

        setPixel(packet, led, red, green, blue);


    }
}

function getPixelNumber(row, col) {
    return row + (col * MAX_ROWS);
}

function setAllPixels(packet, red, green, blue) {
    for (var row = 0; row < MAX_ROWS; row++) {
        for (var col = 0; col < MAX_COLS; col++) {
            setPixelRC(packet, row, col, red, green, blue);
        }
    }

    //    for (var i = 0; i < MAX_LEDS; i++) {
    //        setPixel(packet, i, red, green, blue);
    //    }
}

function setPixelRC(packet, row, col, red, green, blue) {
    setPixel(packet, getPixelNumber(row, col), red, green, blue);
}

function setPixel(packet, led, red, green, blue) {
    var dest = 4 + (led * 3); //4 is header, 3 is rgb offset thingy
    packet[dest++] = red;
    packet[dest++] = green;
    packet[dest++] = blue;
}
