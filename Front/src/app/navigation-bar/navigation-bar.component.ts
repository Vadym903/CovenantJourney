import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { User } from '../models/user.model';
import { AuthService } from '../_services/auth-service.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  user: User;
  currentLang = 'UK';
  langs = [
    {name: 'UK'},
    {name: 'EN'}
  ];

  constructor(private authService: AuthService,
              private router: Router,
              private translateService: TranslateService) {
  }

  ngOnInit(): void {
    this.initMainFields();
    const langFromLocalStorage = localStorage.getItem('lang');
    if (langFromLocalStorage) {
      this.currentLang = langFromLocalStorage;
      this.translateService.use(this.currentLang);
    }

  }

  rediretcTo(url: string): void {
    this.router.navigateByUrl(url);
  }

  openUserPage() {
    this.router.navigateByUrl('user/' + this.user.id).finally(() => window.location.reload());
  }

  logOut(): void {
    this.authService.logOut();
  }

  changeLang(langKey) { // TODO
    this.currentLang = langKey;
    this.translateService.use(langKey);
    localStorage.setItem('lang', langKey);
  }

  private initMainFields(): void {
    this.user = this.initUserImgAndReturnUser(this.authService.getCurrentUser());
    this.authService.currentUser$.subscribe((user: User) => this.user = this.initUserImgAndReturnUser(user));
  }

  private initUserImgAndReturnUser(user: User): User {
    if (user) {
      user.photoUrl = User.getPhotoUrl(user);
      console.log(user.photoUrl);
      return user;
    }
    return null;
  }
}
