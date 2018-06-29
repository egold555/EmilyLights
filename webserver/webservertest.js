//based on https://adrianmejia.com/blog/2016/08/24/building-a-node-js-static-file-server-files-over-http-using-es6/
const http = require('http');
const url = require('url');
const fs = require('fs');
const path = require('path');
// you can pass the parameter in the command line. e.g. node static_server.js 3000
const port = process.argv[2] || 9000;

const util = require('util');
const setTimeoutPromise = util.promisify(setTimeout);

var scenes = require('./scenes.js');

var selectedScene = 0;

var OPC = require('./opc')
var client = new OPC('192.168.1.122', 7890);

var SceneClass = require('./classexample');
var sceneObj = new SceneClass.("Hi");

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



    //override stuff and things
    if (pathname.includes('scene')) {
        var objs = pathname.split("/");
        objs.splice(0, 2);
        selectedScene = parseInt(objs[0]);
        res.end(`Scene set to: ` + selectedScene + "\nRandom: " + Math.random());
        return;
    }

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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
console.log(JSON.stringify(sceneObj.options));
scenes._INIT(client);

function coolTimer() {
    if (selectedScene != -1) {
        scenes._LOOP(selectedScene);
    }
}

setInterval(coolTimer, 200);
