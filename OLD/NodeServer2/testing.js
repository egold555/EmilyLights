//based on https://adrianmejia.com/blog/2016/08/24/building-a-node-js-static-file-server-files-over-http-using-es6/
const http = require('http');
const url = require('url');
const fs = require('fs');
const path = require('path');
// you can pass the parameter in the command line. e.g. node static_server.js 3000
const port = process.argv[2] || 9000;
http.createServer(function (req, res) {
    console.log(`${req.method} ${req.url}`);
    // parse URL
    const parsedUrl = url.parse(req.url);
    // extract URL path
    let pathname = `.${parsedUrl.pathname}`;
    // maps file extention to MIME types
    const mimeType = {
        '.ico': 'image/x-icon',
        '.html': 'text/html',
        '.js': 'text/javascript',
        '.json': 'application/json',
        '.css': 'text/css',
        '.png': 'image/png',
        '.jpg': 'image/jpeg',
        '.wav': 'audio/wav',
        '.mp3': 'audio/mpeg',
        '.svg': 'image/svg+xml',
        '.pdf': 'application/pdf',
        '.doc': 'application/msword',
        '.eot': 'appliaction/vnd.ms-fontobject',
        '.ttf': 'aplication/font-sfnt'
    };
    fs.exists(pathname, function (exist) {
        if (!exist) {
            // if the file is not found, return 404
            res.statusCode = 404;
            res.end(`File ${pathname} not found!`);
            return;
        }
        // if is a directory, then look for index.html
        if (fs.statSync(pathname).isDirectory()) {
            pathname += '/index.html';
        }

        if (pathname.includes('/data')) {

            magicalTestFunction();
            res.end(`I called your magical function! ` + Math.random());
        }

        // read file from file system
        fs.readFile(pathname, function (err, data) {
            if (err) {
                res.statusCode = 500;
                res.end(`Error getting the file: ${err}.`);
            } else {
                // based on the URL path, extract the file extention. e.g. .js, .doc, ...
                const ext = path.parse(pathname).ext;
                // if the file is found, set Content-type and send data
                res.setHeader('Content-type', mimeType[ext] || 'text/plain');
                res.end(data);
            }
        });
    });
}).listen(parseInt(port));
console.log(`Server listening on port ${port}`);

var socket = new WebSocket('ws://192.168.1.122:7890');

const leds = 64;

function magicalTestFunction() {
    var packet = new Uint8ClampedArray(4 + leds * 3);
    if (socket.readyState != 1 /* OPEN */ ) {
        // The server connection isn't open. Nothing to do.
        return;
    }

    if (socket.bufferedAmount > packet.length) {
        // The network is lagging, and we still haven't sent the previous frame.
        // Don't flood the network, it will just make us laggy.
        // If fcserver is running on the same computer, it should always be able
        // to keep up with the frames we send, so we shouldn't reach this point.
        return;
    }

    for (var led = 0; led < leds; led++) {

        var red = Math.sin(frequency * changer + 0) * 127 + 128;
        var green = Math.sin(frequency * changer + 2) * 127 + 128;
        var blue = Math.sin(frequency * changer + 4) * 127 + 128;

        changer += 1;

        setPixel(packet, led, red, green, blue);


    }
    socket.send(packet.buffer);

}

function setPixel(packet, led, red, green, blue) {
    var dest = 4 + (led * 3); //4 is header, 3 is rgb offset thingy
    packet[dest++] = red;
    packet[dest++] = green;
    packet[dest++] = blue;
    console.log(led);
}
