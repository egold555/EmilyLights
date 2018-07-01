const MAX_ROWS = 9;
const MAX_COLS = 11;

const MAX_LEDS = MAX_ROWS * MAX_COLS;

//COOL PATTERNS: https://github.com/pixelmatix/aurora

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

    setColumn(col, red, green, blue) {
        for (var row = 0; row < MAX_ROWS; row++) {
            this.setPixel(row, col, red, green, blue);
        }
    }

    setRow(row, red, green, blue) {
        for (var col = 0; col < MAX_COLS; col++) {
            this.setPixel(row, col, red, green, blue);
        }
    }

    setLineRow(row, start, end, red, green, blue) {
        for (var col = start; col < end; col++) {
            this.setPixel(row, col, red, green, blue);
        }
    }

    setLineCol(col, start, end, red, green, blue) {
        for (var row = start; row < end; row++) {
            this.setPixel(row, col, red, green, blue);
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

    lerp(v0, v1, t) {
        return v0 * (1 - t) + v1 * t
    }

}

class SceneDots extends SceneBase {


    constructor(client) {
        super(client);

        this.advance = 0.02;
        this.hue = 0;
        this.hueAdvance = 0.0001;

        this.dotArray = [];
        for (var light = 0; light < MAX_LEDS; light++) {
            this.dotArray.push({
                start: Math.random(),
                end: Math.random(),
                current: Math.random()
            });
        }
    }

    loop() {
        for (var light = 0; light < MAX_LEDS; light++) {
            var l = this.dotArray[light];
            var lightValue = this.lerp(l.start, l.end, l.current);
            var rgb = this.HSVtoRGB(this.hue, 1, lightValue);
            this.client.setPixel(light, rgb[0], rgb[1], rgb[2]);

            l.current += this.advance;
            if (l.current > 1) {
                l.start = l.end;
                l.end = Math.random();
                l.current = 0;
            }
        }

        this.hue += this.hueAdvance;

        this.client.writePixels();
    }

}

class SceneTest extends SceneBase {
    constructor(client) {
        super(client);
        this.start = new Date().valueOf();
        this.paddleY = 0;
        this.paddleDirection = 1;
    }

    loop() {

        //this is suppost to be like the uw logo, yeah its pretty bad
        //        this.setAllPixels(255, 208, 125);
        //        this.setColumn(2, 205, 0, 255);
        //
        //        this.setPixel(7, 3, 205, 0, 255);
        //        this.setPixel(6, 4, 205, 0, 255);
        //
        //        this.setPixel(5, 5, 205, 0, 255);
        //
        //        this.setPixel(7, 7, 205, 0, 255);
        //        this.setPixel(6, 6, 205, 0, 255);
        //
        //        this.setColumn(8, 205, 0, 255);



        //        this.setAllPixels(0, 0, 0); //reset
        //
        //        this.setRow(0, 0, 255, 0); //top
        //        this.setRow(8, 0, 255, 0); //bottom
        //        this.setColumn(0, 0, 0, 255); //left
        //        this.setColumn(10, 0, 0, 255); //right
        //
        //        //moving paddle
        //        var elapsedPaddle = new Date().valueOf() - this.start;
        //        if (elapsedPaddle > 600) {
        //            this.start = new Date().valueOf();
        //            if (this.paddleY > 4) {
        //                this.paddleDirection = -1;
        //            } else if (this.paddleY <= 0) {
        //                this.paddleDirection = 1;
        //            }
        //            this.paddleY += this.paddleDirection;
        //        }
        //        this.setLineCol(0, this.paddleY, this.paddleY + 4, 255, 0, 0);
        //


        this.client.writePixels();
    }

    //    drawBar(col, height, red, green, blue) {
    //        this.setColumn(col, 0, 0, 0);
    //        for (var newHight = 0; newHight < height; newHight++) {
    //            this.setPixel(newHight, col, red, green, blue);
    //        }
    //    }

}

class SceneRainbow extends SceneBase {

    constructor(client) {
        super(client);
        this.frequency = .3;
        this.changer = 0;
    }



    loop() {

        var time = new Date().valueOf();

        for (var c = 0; c < MAX_COLS; c++) {
            for (var r = 0; r < MAX_ROWS; r++) {
                var hue = (r / 10) - (time / 10000);
                var rgbColor = this.HSVtoRGB(hue, 1, 1);
                this.setPixel(r, c, rgbColor[0], rgbColor[1], rgbColor[2]);
            }
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
