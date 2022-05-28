import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { User } from "../../_models/user.model";
import { UserService } from "../../services/user.service";
import { DomSanitizer } from "@angular/platform-browser";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ApartmentService } from "../../services/apartment.service";
import { Apartment } from "../../_models/apartment.model";

@Component({
	selector: 'app-user-info',
	templateUrl: './user-info.component.html',
	styleUrls: ['./user-info.component.scss']
})
export class UserInfoComponent implements OnInit {

	user: User;
	userDescriptionHtml;
	isEdit = false;
	userForm: FormGroup = new FormGroup({});
	apartments: Apartment[] = [];
	
	constructor(private activatedRoute: ActivatedRoute,
				private userService: UserService,
				private apartmentService: ApartmentService,
				private fb: FormBuilder,
				private sanitizer: DomSanitizer) {
	}

	ngOnInit(): void {
		const userId = +this.activatedRoute.snapshot.paramMap.get('id');
		this.userService.getById$(userId).subscribe(user => {
			this.user = user;
			this.userDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(user.description);
		});
	}

	editMode(): void {
		this.isEdit = true;
		this.initUserForm();
	}

	initUserForm(): void {
		this.userForm = this.fb.group({
			fullName: this.fb.control(this.user.fullName),
			description: this.fb.control(this.user.description)
		});
	}

	cancel(): void {
		this.initUserForm();
		this.isEdit = false;
	}

	save(): void {
		this.userService.updateCurrentUser(this.userForm.value).subscribe(user => {
			this.initUser(user);
			this.cancel();
		});
	}

	initUser(user: User): void {
		this.user = user;
		this.userDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(user.description);
	}

}
