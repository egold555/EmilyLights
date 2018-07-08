import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { DesignerPage } from '../pages/designer/designer';
import { SettingsPage } from '../pages/settings/settings';
import { ScenesPage } from '../pages/scenes/scenes';

import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { ColorPicker } from '../components/color-picker/color-picker';
import { HttpModule } from '@angular/http';
import { IonicStorageModule } from '@ionic/storage';

import { ColorPage } from '../pages/designer/designer';
import { ColorPickerPage } from '../pages/designer/designer';

import { SceneOptionCircles } from '../pages/designer/designer';
import { SceneOptionDots } from '../pages/designer/designer';
import { SceneOptionGradient } from '../pages/designer/designer';
import { SceneOptionRainDrops } from '../pages/designer/designer';

@NgModule({
  declarations: [
    MyApp,
    DesignerPage,
    SettingsPage,
    ScenesPage,
    TabsPage,
    ColorPicker,
    ColorPage,
    ColorPickerPage,
    SceneOptionCircles,
    SceneOptionDots,
    SceneOptionGradient,
    SceneOptionRainDrops
  ],
  imports: [
    HttpModule,
    BrowserModule,
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    DesignerPage,
    SettingsPage,
    ScenesPage,
    TabsPage,
    ColorPage,
    ColorPickerPage,
    SceneOptionCircles,
    SceneOptionDots,
    SceneOptionGradient,
    SceneOptionRainDrops
  ],
  providers: [
    StatusBar,
    SplashScreen,
    { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
