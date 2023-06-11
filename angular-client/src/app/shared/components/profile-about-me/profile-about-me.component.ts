import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-profile-about-me',
  templateUrl: './profile-about-me.component.html',
  styleUrls: ['./profile-about-me.component.css']
})
export class ProfileAboutMeComponent {
@Input() description : string | undefined
}
