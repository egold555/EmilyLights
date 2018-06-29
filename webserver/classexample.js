class SceneBase {

    constructor(optionsIn) {
        this.options = optionsIn;
    }

    loop() {
        throw new Error('You have to implement the method loop!');
    }

    static getPixelNumber(row, col) {
        return row + (col * MAX_ROWS);
    }

    static setAllPixels(red, green, blue) {
        for (var row = 0; row < MAX_ROWS; row++) {
            for (var col = 0; col < MAX_COLS; col++) {
                setPixel1(row, col, red, green, blue);
            }
        }
    }

    static setPixel1(row, col, red, green, blue) {
        client.setPixel(getPixelNumber(row, col), red, green, blue);
    }

}

class SceneDots extends SceneBase {

}

//you need this: https://stackoverflow.com/a/42553756
module.exports = {
    SceneBase: SceneBase,
    SceneDots: SceneDots
};

/*

Scene class:

  - Loop function
      -- when you extend it (abstract)
      -- setPixel functions

  - Variables
    - Options 

*/
