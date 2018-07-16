import { Component } from '@angular/core';

//import { DesignerPage } from '../designer/designer';
import { SettingsPage } from '../settings/settings';
import { ScenesPage } from '../scenes/scenes';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = ScenesPage;
//  tab2Root = DesignerPage;
  tab3Root = SettingsPage;

  constructor() {

  }
}
