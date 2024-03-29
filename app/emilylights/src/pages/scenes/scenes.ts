import { Component } from '@angular/core';
import { NavController, reorderArray, AlertController, ToastController } from 'ionic-angular';
import { Http } from '@angular/http';
import { DesignerPage } from '../designer/designer';

@Component({
  selector: 'page-scenes',
  templateUrl: 'scenes.html'
})
export class ScenesPage {

  scenes: any[];
  editButton: string = 'Reorder';
  editing: boolean = false;

  constructor(public navCtrl: NavController, public alertCtrl: AlertController, public http: Http, public toastCtrl: ToastController) {

  }

  refreshData(refresher) {

    this.http.post(this.getURL("scenes.json"), '').subscribe(
      data => {
        var dataRecieved = data.text(); //._body ???
        //console.log(JSON.stringify(data));
        console.log(JSON.parse(dataRecieved).scenes);
        this.scenes = JSON.parse(dataRecieved).scenes;
        //this.showToast("Refreshed scenes.");
      },
      err => {
        console.log("Oops!");
        console.log(JSON.stringify(err));
        this.showErrorMessage(JSON.stringify(err));
      }
    );

    setTimeout(() => {
      if (refresher != null) {
        refresher.complete();
      }
    }, 2000);
  }

  getURL(file: string) {
    return "/" + file;
  }

  sendPost(prefix: string, data: string) {
    this.http.post(this.getURL(prefix), data).subscribe();
  }

  toggleEdit() {
    this.editing = !this.editing;
    if (this.editing) {
      this.editButton = 'Done';
    } else {
      this.editButton = 'Reorder';
    }
  }

  reorderData(indexes: any) {
    this.scenes = reorderArray(this.scenes, indexes);
  }

  itemApply(item: any) {
    this.sendPost('set/' + item.id, "");
  }

  itemEdit(item: any) {
    this.navCtrl.push(DesignerPage, { id: item.id });
  }

  itemAdd() {
    this.navCtrl.push(DesignerPage, {});
  }

  ionViewDidEnter() {
    this.refreshData(null);
  }

  showToast(msg: string) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 2000,
      position: "bottom"
    });

    toast.present(toast);
  }

  showErrorMessage(error: string) {
    const alert = this.alertCtrl.create({
      title: 'Oops. An error has occurred.',
      subTitle: error,
      buttons: ['OK']
    });
    alert.present();
    console.error("An error occurred!");
    console.error(error);
  }

  itemDelete(scene: any) {
    let prompt = this.alertCtrl.create({
      title: 'Delete "' + scene.name + '"?',
      message: 'Are you sure you want to permanently delete the scene "' + scene.name + '"?',
      buttons: [
        {
          text: 'Cancel',
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Delete "' + scene.name + '"',
          handler: data => {
            console.log('Delete clicked');
            this.showToast(scene.name + " deleted.");
            this.sendPost("delete/" + scene.id, "");
            this.refreshData(null);
          }
        }
      ]
    });
    prompt.present();
  }

}
