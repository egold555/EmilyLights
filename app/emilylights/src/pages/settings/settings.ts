import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ToastController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { NetworkInterface } from '@ionic-native/network-interface';
import { Platform } from 'ionic-angular';

@Component({
  selector: 'page-settings',
  templateUrl: 'settings.html'
})
export class SettingsPage {

  constructor(public navCtrl: NavController, public toastCtrl: ToastController, private storage: Storage, public networkInterface: NetworkInterface, public platform: Platform) {
    

  }

  ionViewDidLoad() {
    
  }

  showToast(msg: string) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 2000,
      position: "bottom"
    });

    toast.present(toast);
  }

  data = {
    wall_enabled: true,
    wall_brightness: 1
  };
}
