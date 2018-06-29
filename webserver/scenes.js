const MAX_ROWS = 8;
const MAX_COLS = 8;

const MAX_LEDS = MAX_ROWS * MAX_COLS;

class SceneBase {

    constructor(client) {
        this.client = client;
    }

    loop() {
        throw new Error('You have to implement the method loop!');
    }

    static getPixelNumber(row, col) {
        return row + (col * MAX_ROWS);
    }

    static setAllPixels(client, red, green, blue) {
        for (var row = 0; row < MAX_ROWS; row++) {
            for (var col = 0; col < MAX_COLS; col++) {
                setPixel1(row, col, red, green, blue);
            }
        }
    }

    static setPixel1(client, row, col, red, green, blue) {
        client.setPixel(getPixelNumber(row, col), red, green, blue);
    }

}

//TODO
class SceneDots extends SceneBase {

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
    SceneRainbow: SceneRainbow
};

/*

Scene class:

  - Loop function
      -- when you extend it (abstract)
      -- setPixel functions

  - Variables
    - Options 

*/
