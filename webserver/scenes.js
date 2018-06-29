const MAX_ROWS = 9;
const MAX_COLS = 11;

const MAX_LEDS = MAX_ROWS * MAX_COLS;

class SceneBase {

    constructor(client) {
        this.client = client;
    }

    loop() {
        throw new Error('You have to implement the method loop!');
    }

    getPixelNumber(row, col) {
        return row + (col * MAX_ROWS);
    }

    setAllPixels(red, green, blue) {
        for (var row = 0; row < MAX_ROWS; row++) {
            for (var col = 0; col < MAX_COLS; col++) {
                this.setPixel(row, col, red, green, blue);
            }
        }
    }

    setPixel(row, col, red, green, blue) {
        this.client.setPixel(this.getPixelNumber(row, col), red, green, blue);
    }

    HSVtoRGB(h, s, v) {
        h = (h % 1) * 6;
        if (h < 0) h += 6;

        var i = h | 0,
            f = h - i,
            p = v * (1 - s),
            q = v * (1 - f * s),
            t = v * (1 - (1 - f) * s),
            r = [v, q, p, p, t, v][i],
            g = [t, v, v, q, p, p][i],
            b = [p, p, t, v, v, q][i];

        return [r * 255, g * 255, b * 255];
    }

}

//TODO https://youtu.be/k_MiqGQ8rG0?t=2m11s
class SceneDots extends SceneBase {

}

class SceneTest extends SceneBase {
    constructor(client) {
        super(client);
        //        this.counter = 0;
        //        for (var r = 0; r < MAX_ROWS; r++) {
        //            for (var c = 0; c < MAX_COLS; c++) {
        //                console.log('Patch_Pixel_X_' + r + '_Y_' + c + '_Ch_R=' + this.counter++);
        //                console.log('Patch_Pixel_X_' + r + '_Y_' + c + '_Ch_G=' + this.counter++);
        //                console.log('Patch_Pixel_X_' + r + '_Y_' + c + '_Ch_B=' + this.counter++);
        //                console.log('Patch_Pixel_X_' + r + '_Y_' + c + '_Uni_ID=0');
        //            }
        //        }
    }

    loop() {
        var time = new Date().valueOf();

        for (var c = 0; c < MAX_COLS; c++) {
            var hue = (c / 10) - (time / 10000);
            var rgbColor = this.HSVtoRGB(hue, 1, 1);
            for (var r = 0; r < MAX_ROWS; r++) {
                this.setPixel(r, c, rgbColor[0], rgbColor[1], rgbColor[2]);
            }
        }



        this.client.writePixels();
    }

}

class SceneRainbow extends SceneBase {

    constructor(client) {
        super(client);
        this.frequency = .3;
        this.changer = 0;
    }



    loop() {

        for (var led = 0; led < MAX_LEDS; led++) {

            var red = Math.sin(this.frequency * this.changer + 0) * 127 + 128;
            var green = Math.sin(this.frequency * this.changer + 2) * 127 + 128;
            var blue = Math.sin(this.frequency * this.changer + 4) * 127 + 128;

            this.changer += 1;

            this.client.setPixel(led, red, green, blue);

        }
        this.client.writePixels();
    }
}

//you need this: https://stackoverflow.com/a/42553756
module.exports = {
    SceneBase: SceneBase,
    SceneDots: SceneDots,
    SceneRainbow: SceneRainbow,
    SceneTest: SceneTest
};

/*

Scene class:

  - Loop function
      -- when you extend it (abstract)
      -- setPixel functions

  - Variables
    - Options 

*/
