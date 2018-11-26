webpackJsonp([0],{

/***/ 111:
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = 111;

/***/ }),

/***/ 152:
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = 152;

/***/ }),

/***/ 195:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TabsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__settings_settings__ = __webpack_require__(196);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__scenes_scenes__ = __webpack_require__(199);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

//import { DesignerPage } from '../designer/designer';


var TabsPage = /** @class */ (function () {
    function TabsPage() {
        this.tab1Root = __WEBPACK_IMPORTED_MODULE_2__scenes_scenes__["a" /* ScenesPage */];
        //  tab2Root = DesignerPage;
        this.tab3Root = __WEBPACK_IMPORTED_MODULE_1__settings_settings__["a" /* SettingsPage */];
    }
    TabsPage = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\tabs\tabs.html"*/'<ion-tabs>\n    <ion-tab [root]="tab1Root" tabTitle="Scenes" tabIcon="albums"></ion-tab>\n    <!--  <ion-tab [root]="tab2Root" tabTitle="Designer" tabIcon="brush"></ion-tab>-->\n    <ion-tab [root]="tab3Root" tabTitle="Settings" tabIcon="build"></ion-tab>\n</ion-tabs>\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\tabs\tabs.html"*/
        }),
        __metadata("design:paramtypes", [])
    ], TabsPage);
    return TabsPage;
}());

//# sourceMappingURL=tabs.js.map

/***/ }),

/***/ 196:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SettingsPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_storage__ = __webpack_require__(197);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_network_interface__ = __webpack_require__(198);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var SettingsPage = /** @class */ (function () {
    function SettingsPage(navCtrl, toastCtrl, storage, networkInterface, platform) {
        var _this = this;
        this.navCtrl = navCtrl;
        this.toastCtrl = toastCtrl;
        this.storage = storage;
        this.networkInterface = networkInterface;
        this.platform = platform;
        this.value_ip = "";
        this.data = {
            wall_enabled: true,
            wall_brightness: 1
        };
        //    storage.get(Constants.StorageKeys._IP).then((val) => {
        //      if (val == null) {
        //        storage.set(Constants.StorageKeys._IP, "127.0.0.1");
        //      }
        //      Constants.GlobalVariables._IP = val;
        //      this.value_ip = Constants.GlobalVariables._IP;
        //    });
        platform.ready().then(function () {
            _this.networkInterface.getWiFiIPAddress()
                .then(function (address) { return console.info("IP: " + address.ip + ", Subnet: " + address.subnet); })
                .catch(function (error) { return console.error("Unable to get IP: " + error); });
        });
    }
    SettingsPage.prototype.ionViewDidLoad = function () {
        //console.log("============= LOADED ==============");
        this.networkInterface.getWiFiIPAddress()
            .then(function (address) { return console.info("IP: " + address.ip + ", Subnet: " + address.subnet); })
            .catch(function (error) { return console.error("Unable to get IP: " + error); });
    };
    SettingsPage.prototype.showToast = function (msg) {
        var toast = this.toastCtrl.create({
            message: msg,
            duration: 2000,
            position: "bottom"
        });
        toast.present(toast);
    };
    SettingsPage.prototype.onIPChange = function (event) {
        this.showToast("IP successfully changed!");
        //this.storage.set(Constants.StorageKeys._IP, event.srcElement.value);
    };
    SettingsPage = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'page-settings',template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\settings\settings.html"*/'<ion-header>\n    <ion-navbar>\n        <ion-title>Settings</ion-title>\n    </ion-navbar>\n</ion-header>\n\n<ion-content class=" cards-bg">\n    <ion-card class="cards-list-demo">\n        <ion-card-header>General</ion-card-header>\n        <ion-list>\n\n            <ion-item>\n                <ion-label>Enabled</ion-label>\n                <ion-toggle [(ngModel)]="wall_enabled" checked="true"></ion-toggle>\n            </ion-item>\n\n            <ion-item>\n                <ion-label>Brightness</ion-label>\n                <ion-range [(ngModel)]="wall_brightness">\n                    <ion-icon range-left small name="sunny"></ion-icon>\n                    <ion-icon range-right name="sunny"></ion-icon>\n                </ion-range>\n            </ion-item>\n\n        </ion-list>\n    </ion-card>\n\n    <ion-card class="cards-list-demo">\n        <ion-card-header>Web Server</ion-card-header>\n        <ion-list>\n\n            <ion-item>\n                <ion-label fixed>IP</ion-label>\n                <ion-input id="id_ip" type="text" [(ngModel)]="value_ip" (keypress)="onIPChange($event)"></ion-input>\n            </ion-item>\n\n        </ion-list>\n    </ion-card>\n\n</ion-content>\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\settings\settings.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ToastController */], __WEBPACK_IMPORTED_MODULE_2__ionic_storage__["b" /* Storage */], __WEBPACK_IMPORTED_MODULE_3__ionic_native_network_interface__["a" /* NetworkInterface */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Platform */]])
    ], SettingsPage);
    return SettingsPage;
}());

//# sourceMappingURL=settings.js.map

/***/ }),

/***/ 199:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ScenesPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__designer_designer__ = __webpack_require__(24);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ScenesPage = /** @class */ (function () {
    function ScenesPage(navCtrl, alertCtrl, http, toastCtrl) {
        this.navCtrl = navCtrl;
        this.alertCtrl = alertCtrl;
        this.http = http;
        this.toastCtrl = toastCtrl;
        this.editButton = 'Edit';
        this.editing = false;
    }
    ScenesPage.prototype.refreshData = function (refresher) {
        var _this = this;
        this.http.post(this.getURL("scenes.json"), '').subscribe(function (data) {
            var dataRecieved = data.text(); //._body ???
            ////console.log(JSON.stringify(data));
            //console.log(JSON.parse(dataRecieved).scenes);
            _this.scenes = JSON.parse(dataRecieved).scenes;
            //this.showToast("Refreshed scenes.");
        }, function (err) {
            //console.log("Oops!");
            //console.log(JSON.stringify(err));
            _this.showErrorMessage(JSON.stringify(err));
        });
        setTimeout(function () {
            if (refresher != null) {
                refresher.complete();
            }
        }, 2000);
    };
    ScenesPage.prototype.getURL = function (file) {
        return "/" + file;
    };
    ScenesPage.prototype.sendPost = function (prefix, data) {
        this.http.post(this.getURL(prefix), data).subscribe();
    };
    ScenesPage.prototype.toggleEdit = function () {
        this.editing = !this.editing;
        if (this.editing) {
            this.editButton = 'Done';
        }
        else {
            this.editButton = 'Reorder';
        }
    };
    ScenesPage.prototype.reorderData = function (indexes) {
        this.scenes = Object(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["i" /* reorderArray */])(this.scenes, indexes);
    };
    ScenesPage.prototype.itemApply = function (item) {
        this.sendPost('set/' + item.id, "");
    };
    ScenesPage.prototype.itemEdit = function (item) {
        this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_3__designer_designer__["c" /* DesignerPage */], { id: item.id });
    };
    ScenesPage.prototype.itemAdd = function () {
        this.navCtrl.push(__WEBPACK_IMPORTED_MODULE_3__designer_designer__["c" /* DesignerPage */], {});
    };
    ScenesPage.prototype.ionViewDidEnter = function () {
        this.refreshData(null);
    };
    ScenesPage.prototype.showToast = function (msg) {
        var toast = this.toastCtrl.create({
            message: msg,
            duration: 2000,
            position: "bottom"
        });
        toast.present(toast);
    };
    ScenesPage.prototype.showErrorMessage = function (error) {
        var alert = this.alertCtrl.create({
            title: 'Oops. An error has occurred.',
            subTitle: error,
            buttons: ['OK']
        });
        alert.present();
        console.error("An error occurred!");
        console.error(error);
    };
    ScenesPage.prototype.itemDelete = function (scene) {
        var _this = this;
        var prompt = this.alertCtrl.create({
            title: 'Delete "' + scene.name + '"?',
            message: 'Are you sure you want to permanently delete the scene "' + scene.name + '"?',
            buttons: [
                {
                    text: 'Cancel',
                    handler: function (data) {
                        //console.log('Cancel clicked');
                    }
                },
                {
                    text: 'Delete "' + scene.name + '"',
                    handler: function (data) {
                        //console.log('Delete clicked');
                        _this.showToast(scene.name + " deleted.");
                        _this.sendPost("delete/" + scene.id, "");
                        _this.refreshData(null);
                    }
                }
            ]
        });
        prompt.present();
    };
    ScenesPage = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'page-scenes',template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\scenes\scenes.html"*/'<ion-header>\n    <ion-navbar>\n        <ion-title>Scenes</ion-title>\n        <ion-buttons end>\n            <button ion-button (click)="toggleEdit()">{{editButton}}</button>\n        </ion-buttons>\n    </ion-navbar>\n</ion-header>\n\n<ion-content class="outer-content">\n\n    <ion-refresher (ionRefresh)="refreshData($event)">\n        <ion-refresher-content pullingIcon="arrow-dropdown" pullingText="Pull to refresh" refreshingSpinner="circles" refreshingText="Refreshing...">\n        </ion-refresher-content>\n    </ion-refresher>\n\n    <ion-list [reorder]="editing" (ionItemReorder)="reorderData($event)">\n\n        <ion-item-sliding *ngFor="let scene of scenes">\n            <button ion-item (click)="itemApply(scene)">\n                <ion-thumbnail item-start>\n                    <img src="http://192.168.1.122:8000/previmgs/{{scene.id}}">\n                </ion-thumbnail>\n                <h2>{{ scene.name }}</h2>\n            </button>\n            <ion-item-options>\n                <button ion-button (click)="itemEdit(scene)" color="secondary" icon-start><ion-icon name="brush"></ion-icon>Edit</button>\n                <button ion-button (click)="itemDelete(scene)" color="danger" icon-start><ion-icon name="trash"></ion-icon>Delete</button>\n            </ion-item-options>\n\n        </ion-item-sliding>\n\n    </ion-list>\n\n    <ion-fab right bottom>\n        <button ion-fab (click)="itemAdd()"><ion-icon name="add"></ion-icon></button>\n    </ion-fab>\n\n</ion-content>\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\scenes\scenes.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ToastController */]])
    ], ScenesPage);
    return ScenesPage;
}());

//# sourceMappingURL=scenes.js.map

/***/ }),

/***/ 200:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(201);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(223);


Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 223:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser__ = __webpack_require__(27);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__(266);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_settings_settings__ = __webpack_require__(196);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_scenes_scenes__ = __webpack_require__(199);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__ = __webpack_require__(195);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__ionic_native_status_bar__ = __webpack_require__(192);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__ionic_native_splash_screen__ = __webpack_require__(194);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__components_color_picker_color_picker__ = __webpack_require__(278);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__angular_http__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__ionic_storage__ = __webpack_require__(197);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__ionic_native_network_interface__ = __webpack_require__(198);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




















var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["I" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["c" /* DesignerPage */],
                __WEBPACK_IMPORTED_MODULE_5__pages_settings_settings__["a" /* SettingsPage */],
                __WEBPACK_IMPORTED_MODULE_6__pages_scenes_scenes__["a" /* ScenesPage */],
                __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__["a" /* TabsPage */],
                __WEBPACK_IMPORTED_MODULE_10__components_color_picker_color_picker__["a" /* ColorPicker */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["a" /* ColorPage */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["b" /* ColorPickerPage */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["d" /* SceneOptionCircles */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["e" /* SceneOptionDots */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["f" /* SceneOptionGradient */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["g" /* SceneOptionRainDrops */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_11__angular_http__["b" /* HttpModule */],
                __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["d" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */], {}, {
                    links: []
                }),
                __WEBPACK_IMPORTED_MODULE_12__ionic_storage__["a" /* IonicStorageModule */].forRoot()
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["b" /* IonicApp */]],
            entryComponents: [
                __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* MyApp */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["c" /* DesignerPage */],
                __WEBPACK_IMPORTED_MODULE_5__pages_settings_settings__["a" /* SettingsPage */],
                __WEBPACK_IMPORTED_MODULE_6__pages_scenes_scenes__["a" /* ScenesPage */],
                __WEBPACK_IMPORTED_MODULE_7__pages_tabs_tabs__["a" /* TabsPage */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["a" /* ColorPage */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["b" /* ColorPickerPage */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["d" /* SceneOptionCircles */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["e" /* SceneOptionDots */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["f" /* SceneOptionGradient */],
                __WEBPACK_IMPORTED_MODULE_4__pages_designer_designer__["g" /* SceneOptionRainDrops */]
            ],
            providers: [
                __WEBPACK_IMPORTED_MODULE_8__ionic_native_status_bar__["a" /* StatusBar */],
                __WEBPACK_IMPORTED_MODULE_9__ionic_native_splash_screen__["a" /* SplashScreen */],
                __WEBPACK_IMPORTED_MODULE_13__ionic_native_network_interface__["a" /* NetworkInterface */],
                { provide: __WEBPACK_IMPORTED_MODULE_0__angular_core__["u" /* ErrorHandler */], useClass: __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["c" /* IonicErrorHandler */] }
            ]
        })
    ], AppModule);
    return AppModule;
}());

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 24:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "g", function() { return SceneOptionRainDrops; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "d", function() { return SceneOptionCircles; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "e", function() { return SceneOptionDots; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "f", function() { return SceneOptionGradient; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return ColorPickerPage; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ColorPage; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return DesignerPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__(102);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SceneOptionRainDrops = /** @class */ (function () {
    function SceneOptionRainDrops(params) {
        this.dropLength = DesignerPage.options.dropLength;
        this.dropValueStart = DesignerPage.options.dropValueStart;
        this.dropMinTime = DesignerPage.options.dropMinTime;
        this.dropMaxTime = DesignerPage.options.dropMaxTime;
        this.speed = DesignerPage.options.speed;
    }
    SceneOptionRainDrops.prototype.ionViewCanLeave = function () {
        DesignerPage.options = { dropLength: this.dropLength, dropValueStart: this.dropValueStart, dropMinTime: this.dropMinTime, dropMaxTime: this.dropMaxTime, speed: this.speed };
    };
    SceneOptionRainDrops = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_RainDrops.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>RainDrops Options</ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content class="outer-content">\n\n\n\n    <ion-item>\n\n        <ion-label>Drop Length</ion-label>\n\n        <ion-input type="number" placeholder="7" value="7" [(ngModel)]="dropLength"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Drop Value Start</ion-label>\n\n        <ion-input type="number" placeholder="0.6" value="0.6" [(ngModel)]="dropValueStart"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Drop Min Time</ion-label>\n\n        <ion-input type="number" placeholder="0.3" value="0.3" [(ngModel)]="dropMinTime"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Drop Max Time</ion-label>\n\n        <ion-input type="number" placeholder="1" value="1" [(ngModel)]="dropMaxTime"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Speed</ion-label>\n\n        <ion-input type="number" placeholder="10" value="10" [(ngModel)]="speed"></ion-input>\n\n    </ion-item>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_RainDrops.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], SceneOptionRainDrops);
    return SceneOptionRainDrops;
}());

var SceneOptionCircles = /** @class */ (function () {
    function SceneOptionCircles(params) {
        this.width = DesignerPage.options.width;
        this.speed = DesignerPage.options.speed;
        this.dropMinTime = DesignerPage.options.dropMinTime;
        this.dropMaxTime = DesignerPage.options.dropMaxTime;
    }
    SceneOptionCircles.prototype.ionViewCanLeave = function () {
        DesignerPage.options = { width: this.width, speed: this.speed, dropMinTime: this.dropMinTime, dropMaxTime: this.dropMaxTime };
    };
    SceneOptionCircles = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Circles.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>Circle Options</ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content class="outer-content">\n\n\n\n    <ion-item>\n\n        <ion-label>Width</ion-label>\n\n        <ion-input type="number" placeholder="1.1" value="1.1" [(ngModel)]="width"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Speed</ion-label>\n\n        <ion-input type="number" placeholder="0.07" value="0.07" [(ngModel)]="speed"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Drop Min Time</ion-label>\n\n        <ion-input type="number" placeholder="2.5" value="2.5" [(ngModel)]="dropMinTime"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Drop Max Time</ion-label>\n\n        <ion-input type="number" placeholder="8" value="8" [(ngModel)]="dropMaxTime"></ion-input>\n\n    </ion-item>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Circles.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], SceneOptionCircles);
    return SceneOptionCircles;
}());

var SceneOptionDots = /** @class */ (function () {
    function SceneOptionDots(params) {
        this.advance = DesignerPage.options.advance;
        this.colorAdvance = DesignerPage.options.colorAdvance;
    }
    SceneOptionDots.prototype.ionViewCanLeave = function () {
        DesignerPage.options = { advance: this.advance, colorAdvance: this.colorAdvance };
    };
    SceneOptionDots = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Dots.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>Dots Options</ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content class="outer-content">\n\n\n\n    <ion-item>\n\n        <ion-label>Advance</ion-label>\n\n        <ion-input type="number" placeholder="0.02" value="0.02" [(ngModel)]="advance"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Color Advance</ion-label>\n\n        <ion-input type="number" placeholder="0.0001" value="0.0001" [(ngModel)]="colorAdvance"></ion-input>\n\n    </ion-item>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Dots.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], SceneOptionDots);
    return SceneOptionDots;
}());

var SceneOptionGradient = /** @class */ (function () {
    function SceneOptionGradient(params) {
        this.direction = DesignerPage.options.direction;
        this.speed = DesignerPage.options.speed;
        this.smoothness = DesignerPage.options.smoothness;
    }
    SceneOptionGradient.prototype.ionViewCanLeave = function () {
        DesignerPage.options = { direction: this.direction, speed: this.speed, smoothness: this.smoothness };
    };
    SceneOptionGradient = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Gradient.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>Gradient Options</ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content class="outer-content">\n\n\n\n    <ion-item>\n\n        <ion-label>Direction</ion-label>\n\n        <ion-select [(ngModel)]="direction">\n\n            <ion-option value="LEFT">LEFT</ion-option>\n\n            <ion-option value="RIGHT">RIGHT</ion-option>\n\n            <ion-option value="TOP">TOP</ion-option>\n\n            <ion-option value="BOTTOM">BOTTOM</ion-option>\n\n            <ion-option value="LEFT_TOP">LEFT_TOP</ion-option>\n\n            <ion-option value="LEFT_BOTTOM">LEFT_BOTTOM</ion-option>\n\n            <ion-option value="RIGHT_TOP">RIGHT_TOP</ion-option>\n\n            <ion-option value="RIGHT_BOTTOM">RIGHT_BOTTOM</ion-option>\n\n            <ion-option value="DIAMOND_IN">DIAMOND_IN</ion-option>\n\n            <ion-option value="DIAMOND_OUT">DIAMOND_OUT</ion-option>\n\n            <ion-option value="SQUARE_IN">SQUARE_IN</ion-option>\n\n            <ion-option value="SQUARE_OUT">SQUARE_OUT</ion-option>\n\n            <ion-option value="CIRCLE_IN">CIRCLE_IN</ion-option>\n\n            <ion-option value="CIRCLE_OUT">CIRCLE_OUT</ion-option>\n\n            <ion-option value="SOLID">SOLID</ion-option>\n\n        </ion-select>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Speed</ion-label>\n\n        <ion-input type="number" placeholder="0.5" value="0.5" [(ngModel)]="speed"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Smoothness</ion-label>\n\n        <ion-input type="number" placeholder="2.0" value="2.0" [(ngModel)]="smoothness"></ion-input>\n\n    </ion-item>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\SO_Gradient.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], SceneOptionGradient);
    return SceneOptionGradient;
}());

var ColorPickerPage = /** @class */ (function () {
    function ColorPickerPage(params) {
        this.color = "#FF0000";
    }
    ColorPickerPage.prototype.setColor = function (event) {
        this.color = event;
    };
    ColorPickerPage.prototype.colorTouchStart = function () {
    };
    ColorPickerPage.prototype.colorTouchEnd = function () {
    };
    ColorPickerPage.prototype.ionViewCanLeave = function () {
        var thisIsDumb = { hex: this.color };
        ColorPage.colors.push(thisIsDumb);
    };
    ColorPickerPage = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\colorpicker.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>Colors Picker</ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content padding>\n\n    <p>Color: {{color}}</p>\n\n    <color-picker [hexColor]="\'#FF0000\'" (colorChanged)="setColor($event)" (colorTouchStart)="colorTouchStart()" (colorTouchEnd)="colorTouchEnd()"></color-picker>\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\colorpicker.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], ColorPickerPage);
    return ColorPickerPage;
}());

var ColorPage = /** @class */ (function () {
    function ColorPage(navCtrl, params) {
        this.navCtrl = navCtrl;
        ColorPage_1.colors = DesignerPage.colors;
        if (ColorPage_1.colors == null) {
            ColorPage_1.colors = [];
        }
    }
    ColorPage_1 = ColorPage;
    Object.defineProperty(ColorPage.prototype, "colors", {
        get: function () {
            return ColorPage_1.colors;
        },
        enumerable: true,
        configurable: true
    });
    ColorPage.prototype.add = function () {
        this.navCtrl.push(ColorPickerPage, {});
    };
    ColorPage.prototype.ionViewCanLeave = function () {
        DesignerPage.colors = ColorPage_1.colors;
    };
    ColorPage.prototype.itemDelete = function (color) {
        //console.log(JSON.stringify(color.hex));
        ColorPage_1.colors = ColorPage_1.colors.filter(function (obj) { return obj !== color; }); //OO Fancy
    };
    ColorPage.colors = [];
    ColorPage = ColorPage_1 = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\color-page.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>Colors</ion-title>\n\n        <!--\n\n        <ion-buttons end>\n\n            <button ion-button (click)="toggleEdit()">{{editButton}}</button>\n\n        </ion-buttons>\n\n-->\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content class="outer-content">\n\n\n\n    <ion-list>\n\n\n\n        <ion-item-sliding *ngFor="let color of colors">\n\n            <ion-item>\n\n                <ion-thumbnail item-start>\n\n                    <div class="color-block" [ngStyle]="{\'background-color\': color.hex}"></div>\n\n                </ion-thumbnail>\n\n                <h2>{{ color.hex }}</h2>\n\n            </ion-item>\n\n            <ion-item-options>\n\n                <button ion-button (click)="itemDelete(color)" color="danger" icon-start><ion-icon name="trash"></ion-icon>Delete</button>\n\n            </ion-item-options>\n\n        </ion-item-sliding>\n\n\n\n    </ion-list>\n\n\n\n    <ion-fab right bottom>\n\n        <button ion-fab (click)="add()"><ion-icon name="add"></ion-icon></button>\n\n    </ion-fab>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\color-page.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* NavController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* NavParams */]])
    ], ColorPage);
    return ColorPage;
    var ColorPage_1;
}());

var DesignerPage = /** @class */ (function () {
    function DesignerPage(navCtrl, http) {
        this.navCtrl = navCtrl;
        this.http = http;
    }
    DesignerPage_1 = DesignerPage;
    DesignerPage.prototype.colorTouchStart = function () {
    };
    DesignerPage.prototype.colorTouchEnd = function () {
    };
    DesignerPage.prototype.openColorPage = function () {
        this.navCtrl.push(ColorPage, {});
    };
    DesignerPage.prototype.openOptions = function () {
        var selected = this.type;
        if (selected != null) {
            if (selected == "0") {
                //Dots
                this.navCtrl.push(SceneOptionDots, {});
            }
            else if (selected == "1") {
                //Gradient
                this.navCtrl.push(SceneOptionGradient, {});
            }
            else if (selected == "2") {
                //Circles
                this.navCtrl.push(SceneOptionCircles, {});
            }
            else if (selected == "3") {
                //RainDrops
                this.navCtrl.push(SceneOptionRainDrops, {});
            }
        }
    };
    DesignerPage.prototype.createScene = function () {
        var postData = this.getPostDataForScenePreviewAndCeate();
        this.sendPost("add", JSON.stringify(postData));
        ColorPage.colors = [];
        DesignerPage_1.colors = [];
        this.navCtrl.pop(); //go back to prev page
    };
    DesignerPage.prototype.previewScene = function () {
        var postData = this.getPostDataForScenePreviewAndCeate();
        this.sendPost("preview", JSON.stringify(postData));
    };
    DesignerPage.prototype.getPostDataForScenePreviewAndCeate = function () {
        var postData = {};
        postData.name = this.value_name;
        postData.type = this.type;
        postData.options = DesignerPage_1.options;
        postData.colors = DesignerPage_1.colors; //not exactly correct butseems to work for the most part
        return postData;
    };
    DesignerPage.prototype.getURL = function (file) {
        return "http://192.168.1.56:8000/" + file;
    };
    DesignerPage.prototype.sendPost = function (prefix, data) {
        this.http.post(this.getURL(prefix), data).subscribe();
    };
    DesignerPage.options = {};
    DesignerPage.colors = [];
    DesignerPage = DesignerPage_1 = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'page-designer',template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\designer.html"*/'<ion-header>\n\n    <ion-navbar>\n\n        <ion-title>\n\n            Designer\n\n        </ion-title>\n\n    </ion-navbar>\n\n</ion-header>\n\n\n\n<ion-content padding>\n\n    <ion-item>\n\n        <ion-label fixed>Name</ion-label>\n\n        <ion-input id="id_name" type="text" [(ngModel)]="value_name"></ion-input>\n\n    </ion-item>\n\n    <ion-item>\n\n        <ion-label>Type</ion-label>\n\n        <ion-select [(ngModel)]="type">\n\n            <ion-option value="0">Dots</ion-option>\n\n            <ion-option value="1">Gradient</ion-option>\n\n            <ion-option value="2">Circles</ion-option>\n\n            <ion-option value="3">Raindrops</ion-option>\n\n        </ion-select>\n\n    </ion-item>\n\n    <br>\n\n    <button ion-button full (click)="openColorPage()">Select Colors</button>\n\n    <br>\n\n    <button ion-button full (click)="openOptions()">Scene Options</button>\n\n\n\n    <br>\n\n    <br>\n\n    <button ion-button full color="danger" (click)="previewScene()">Preview Scene</button>\n\n    <br>\n\n    <button ion-button full color="secondary" (click)="createScene()">Create Scene</button>\n\n\n\n</ion-content>\n\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\pages\designer\designer.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */]])
    ], DesignerPage);
    return DesignerPage;
    var DesignerPage_1;
}());

//# sourceMappingURL=designer.js.mapundefined

/***/ }),

/***/ 266:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__ = __webpack_require__(192);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__ = __webpack_require__(194);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_tabs_tabs__ = __webpack_require__(195);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MyApp = /** @class */ (function () {
    function MyApp(platform, statusBar, splashScreen) {
        this.rootPage = __WEBPACK_IMPORTED_MODULE_4__pages_tabs_tabs__["a" /* TabsPage */];
        platform.ready().then(function () {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
            statusBar.styleDefault();
            splashScreen.hide();
        });
    }
    MyApp = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({template:/*ion-inline-start:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\app\app.html"*/'<ion-nav [root]="rootPage"></ion-nav>\n'/*ion-inline-end:"C:\Users\Eric\Documents\GitHub\EmilyLights\app\emilylights\src\app\app.html"*/
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* Platform */], __WEBPACK_IMPORTED_MODULE_2__ionic_native_status_bar__["a" /* StatusBar */], __WEBPACK_IMPORTED_MODULE_3__ionic_native_splash_screen__["a" /* SplashScreen */]])
    ], MyApp);
    return MyApp;
}());

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 278:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ColorPicker; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var POUCH = [
    {
        START: "mousedown",
        MOVE: "mousemove",
        STOP: "mouseup"
    },
    {
        START: "touchstart",
        MOVE: "touchmove",
        STOP: "touchend"
    }
];
var ColorPicker = /** @class */ (function () {
    function ColorPicker() {
        this.colorChanged = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["v" /* EventEmitter */]();
        this.colorTouchStart = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["v" /* EventEmitter */]();
        this.colorTouchEnd = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["v" /* EventEmitter */]();
    }
    ColorPicker.prototype.ngOnInit = function () {
        if (this.hexColor) {
            this.colorFromChooser = this.hexColor;
        }
        else {
            this.colorFromChooser = "#0000FF";
        }
        this.init();
    };
    ColorPicker.prototype.init = function () {
        this.initChooser();
        this.initPalette();
    };
    ColorPicker.prototype.drawSelector = function (ctx, x, y) {
        this.drawPalette(this.colorFromChooser);
        ctx.beginPath();
        ctx.arc(x, y, 10 * this.getPixelRatio(ctx), 0, 2 * Math.PI, false);
        ctx.fillStyle = this.color;
        ctx.fill();
        ctx.lineWidth = 3;
        ctx.strokeStyle = '#FFFFFF';
        ctx.stroke();
    };
    ColorPicker.prototype.drawChooserSelector = function (ctx, x) {
        this.drawPalette(this.colorFromChooser);
        ctx.beginPath();
        ctx.arc(x, ctx.canvas.height / 2, ctx.canvas.height / 2, 0, 2 * Math.PI, false);
        ctx.fillStyle = this.colorFromChooser;
        ctx.fill();
        ctx.lineWidth = 3;
        ctx.strokeStyle = '#FFFFFF';
        ctx.stroke();
    };
    ColorPicker.prototype.initPalette = function () {
        var _this = this;
        var canvasPalette = this.palette.nativeElement;
        this.ctxPalette = canvasPalette.getContext("2d");
        var currentWidth = window.innerWidth;
        var pixelRatio = this.getPixelRatio(this.ctxPalette);
        //console.log(pixelRatio);
        var width = currentWidth * 90 / 100;
        var height = width * 0.5;
        this.ctxPalette.canvas.width = width * pixelRatio;
        this.ctxPalette.canvas.height = height * pixelRatio;
        this.ctxPalette.canvas.style.width = width + "px";
        this.ctxPalette.canvas.style.height = height + "px";
        this.drawPalette(this.colorFromChooser);
        var eventChangeColor = function (event) {
            _this.updateColor(event, canvasPalette, _this.ctxPalette);
        };
        POUCH.forEach(function (pouch) {
            canvasPalette.addEventListener(pouch.START, function (event) {
                _this.colorTouchStart.emit();
                _this.drawPalette(_this.colorFromChooser);
                canvasPalette.addEventListener(pouch.MOVE, eventChangeColor);
                _this.updateColor(event, canvasPalette, _this.ctxPalette);
            });
            canvasPalette.addEventListener(pouch.STOP, function (event) {
                _this.colorTouchEnd.emit();
                canvasPalette.removeEventListener(pouch.MOVE, eventChangeColor);
                _this.updateColor(event, canvasPalette, _this.ctxPalette);
                _this.drawSelector(_this.ctxPalette, _this.paletteX, _this.paletteY);
            });
        });
    };
    ColorPicker.prototype.drawPalette = function (endColor) {
        if (endColor == null) {
            return;
        }
        this.ctxPalette.clearRect(0, 0, this.ctxPalette.canvas.width, this.ctxPalette.canvas.height);
        var gradient = this.ctxPalette.createLinearGradient(0, 0, this.ctxPalette.canvas.width, 0);
        // Create color gradient
        gradient.addColorStop(0, "#FFFFFF");
        gradient.addColorStop(1, endColor);
        // Apply gradient to canvas
        this.ctxPalette.fillStyle = gradient;
        this.ctxPalette.fillRect(0, 0, this.ctxPalette.canvas.width, this.ctxPalette.canvas.height);
        // Create semi transparent gradient (white -> trans. -> black)
        gradient = this.ctxPalette.createLinearGradient(0, 0, 0, this.ctxPalette.canvas.height);
        gradient.addColorStop(0, "rgba(255, 255, 255, 1)");
        gradient.addColorStop(0.5, "rgba(255, 255, 255, 0)");
        gradient.addColorStop(0.5, "rgba(0,     0,   0, 0)");
        gradient.addColorStop(1, "rgba(0,     0,   0, 1)");
        // Apply gradient to canvas
        this.ctxPalette.fillStyle = gradient;
        this.ctxPalette.fillRect(0, 0, this.ctxPalette.canvas.width, this.ctxPalette.canvas.height);
    };
    ColorPicker.prototype.initChooser = function () {
        var _this = this;
        var canvasChooser = this.chooser.nativeElement;
        var ctx = canvasChooser.getContext("2d");
        var currentWidth = window.innerWidth;
        var pixelRatio = this.getPixelRatio(ctx);
        var width = currentWidth * 90 / 100;
        var height = width * 0.05;
        ctx.canvas.width = width * pixelRatio;
        ctx.canvas.height = height * pixelRatio;
        ctx.canvas.style.width = width + "px";
        ctx.canvas.style.height = height + "px";
        this.drawChooser(ctx);
        //EVENTS For gradient bottom
        var eventChangeColorChooser = function (event) {
            _this.updateColorChooser(event, canvasChooser, ctx);
            _this.drawSelector(_this.ctxPalette, _this.paletteX, _this.paletteY);
            ////console.log("Move");
        };
        POUCH.forEach(function (pouch) {
            canvasChooser.addEventListener(pouch.START, function (event) {
                _this.drawChooser(ctx);
                canvasChooser.addEventListener(pouch.MOVE, eventChangeColorChooser);
                _this.updateColorChooser(event, canvasChooser, ctx);
                _this.drawSelector(_this.ctxPalette, _this.paletteX, _this.paletteY);
                ////console.log("Start");
            });
            canvasChooser.addEventListener(pouch.STOP, function (event) {
                canvasChooser.removeEventListener(pouch.MOVE, eventChangeColorChooser);
                _this.updateColorChooser(event, canvasChooser, ctx);
                _this.drawChooser(ctx);
                _this.drawChooserSelector(ctx, _this.chooserX);
                _this.drawSelector(_this.ctxPalette, _this.paletteX, _this.paletteY);
                ////console.log("Stop");
            });
        });
    };
    ColorPicker.prototype.drawChooser = function (ctx) {
        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
        var gradient = ctx.createLinearGradient(0, 0, ctx.canvas.width, 0);
        // Create color gradient
        gradient.addColorStop(0, "rgb(255,   0,   0)");
        gradient.addColorStop(0.15, "rgb(255,   0, 255)");
        gradient.addColorStop(0.33, "rgb(0,     0, 255)");
        gradient.addColorStop(0.49, "rgb(0,   255, 255)");
        gradient.addColorStop(0.67, "rgb(0,   255,   0)");
        gradient.addColorStop(0.84, "rgb(255, 255,   0)");
        gradient.addColorStop(1, "rgb(255,   0,   0)");
        // Apply gradient to canvas
        ctx.fillStyle = gradient;
        ctx.fillRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    };
    ColorPicker.prototype.getPixelRatio = function (ctx) {
        var dpr = window.devicePixelRatio || 1;
        var bsr = ctx.webkitBackingStorePixelRatio ||
            ctx.mozBackingStorePixelRatio ||
            ctx.msBackingStorePixelRatio ||
            ctx.oBackingStorePixelRatio ||
            ctx.backingStorePixelRatio || 1;
        return dpr / bsr;
    };
    ColorPicker.prototype.updateColorChooser = function (event, canvas, context) {
        var newColor = this.getColor(event, canvas, context, true);
        ;
        if (newColor == null) {
            return;
        }
        this.color = this.colorFromChooser = newColor;
        this.colorChanged.emit(this.color);
        this.drawPalette(this.color);
    };
    ColorPicker.prototype.updateColor = function (event, canvas, context) {
        var newColor = this.getColor(event, canvas, context, false);
        if (newColor == null) {
            return;
        }
        this.color = newColor;
        this.colorChanged.emit(this.color);
    };
    ColorPicker.prototype.getColor = function (event, canvas, context, fromChooser) {
        var bounding = canvas.getBoundingClientRect(), touchX = event.pageX || event.changedTouches[0].pageX || event.changedTouches[0].screenX, touchY = event.pageY || event.changedTouches[0].pageY || event.changedTouches[0].screenX;
        if (touchX >= bounding.right || touchX < bounding.left || touchY >= bounding.bottom || touchY < bounding.top) {
            return null;
        }
        var x = (touchX - bounding.left) * this.getPixelRatio(context);
        var y = (touchY - bounding.top) * this.getPixelRatio(context);
        if (fromChooser) {
            this.chooserX = x;
        }
        else {
            this.paletteX = x;
            this.paletteY = y;
        }
        var imageData = context.getImageData(x, y, 1, 1);
        var red = imageData.data[0];
        var green = imageData.data[1];
        var blue = imageData.data[2];
        return "#" + this.toHex(red) + this.toHex(green) + this.toHex(blue);
    };
    ColorPicker.prototype.toHex = function (n) {
        n = parseInt(n, 10);
        if (isNaN(n))
            return "00";
        n = Math.max(0, Math.min(n, 255));
        return "0123456789ABCDEF".charAt((n - n % 16) / 16) + "0123456789ABCDEF".charAt(n % 16);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["D" /* Input */])(),
        __metadata("design:type", String)
    ], ColorPicker.prototype, "hexColor", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["O" /* Output */])(),
        __metadata("design:type", Object)
    ], ColorPicker.prototype, "colorChanged", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["O" /* Output */])(),
        __metadata("design:type", Object)
    ], ColorPicker.prototype, "colorTouchStart", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["O" /* Output */])(),
        __metadata("design:type", Object)
    ], ColorPicker.prototype, "colorTouchEnd", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_8" /* ViewChild */])("palette"),
        __metadata("design:type", Object)
    ], ColorPicker.prototype, "palette", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_8" /* ViewChild */])("chooser"),
        __metadata("design:type", Object)
    ], ColorPicker.prototype, "chooser", void 0);
    ColorPicker = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'color-picker',
            template: " <canvas #palette style=\"background:white;\" class='center'></canvas>\n  <canvas #chooser style=\"background:white; margin-top: 20px; margin-bottom: 20px; \" class='center'></canvas>"
        })
    ], ColorPicker);
    return ColorPicker;
}());

//# sourceMappingURL=color-picker.js.map

/***/ })

},[200]);
//# sourceMappingURL=main.js.map