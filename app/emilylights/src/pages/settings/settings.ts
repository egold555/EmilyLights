import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ToastController } from 'ionic-angular';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-settings',
  templateUrl: 'settings.html'
})
export class SettingsPage {

  value_ip: string = "";

  constructor(public navCtrl: NavController, public toastCtrl: ToastController, private storage: Storage) {

    //    storage.get(Constants.StorageKeys._IP).then((val) => {
    //      if (val == null) {
    //        storage.set(Constants.StorageKeys._IP, "127.0.0.1");
    //      }
    //      Constants.GlobalVariables._IP = val;
    //      this.value_ip = Constants.GlobalVariables._IP;
    //    });

  }

  showToast(msg: string) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 2000,
      position: "bottom"
    });

    toast.present(toast);
  }

  onIPChange(event: any) { //WebBrowser: gets called every key and is buggy. Android: only gets called on ENTER 
    this.showToast("IP successfully changed!");
    //this.storage.set(Constants.StorageKeys._IP, event.srcElement.value);
  }

  data = {
    wall_enabled: true,
    wall_brightness: 1
  };
}
