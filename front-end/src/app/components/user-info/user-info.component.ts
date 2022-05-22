import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { User } from "../../_models/user.model";
import { UserService } from "../../services/user.service";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
	selector: 'app-user-info',
	templateUrl: './user-info.component.html',
	styleUrls: ['./user-info.component.scss']
})
export class UserInfoComponent implements OnInit {

	user: User;
	userDescriptionHtml;

	constructor(private activatedRoute: ActivatedRoute,
				private userService: UserService,
				private sanitizer: DomSanitizer) {
	}

	ngOnInit(): void {
		const userId = +this.activatedRoute.snapshot.paramMap.get('id');
		this.userService.getById$(userId).subscribe(user => {
			this.user = user;
			this.userDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(user.description);
		});

	}

}
