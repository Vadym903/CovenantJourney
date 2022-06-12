import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { User } from "../../_models/user.model";
import { UserService } from "../../services/user.service";
import { DomSanitizer } from "@angular/platform-browser";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ApartmentService } from "../../services/apartment.service";
import { Apartment } from "../../_models/apartment.model";
import { Feedback } from "../../_models/feedback.model";
import { FeedbackService } from "../../services/feedback.service";
import { Filter } from "../../_models/filter-model";
import { FilteringOperation } from "../../shared/constants/filtering-operations.constants";
import { AuthService } from "../../services/auth-service.service";

@Component({
	selector: 'app-user-info',
	templateUrl: './user-info.component.html',
	styleUrls: ['./user-info.component.scss']
})
export class UserInfoComponent implements OnInit {

	user: User;
	isOwner = false;
	userDescriptionHtml;
	isEdit = false;
	userForm: FormGroup = new FormGroup({});
	apartments: Apartment[] = [];
	feedbacks: Feedback[] = [];

	constructor(private activatedRoute: ActivatedRoute,
				private userService: UserService,
				private authService: AuthService,
				private apartmentService: ApartmentService,
				private feedbackService: FeedbackService,
				private fb: FormBuilder,
				private sanitizer: DomSanitizer) {
	}

	ngOnInit(): void {
		const userId = +this.activatedRoute.snapshot.paramMap.get('id');
		this.userService.getById$(userId).subscribe(user => {
			this.user = user;
			this.userDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(user.description);
			this.isOwner = user.id == this.authService.getCurrentUser().id;
		});

		const filter = new Filter("owner", FilteringOperation.EQUAL, userId + '');

		this.apartmentService.getPage$(0, 200, [], [filter])
			.subscribe(page => this.apartments = page.items);

		this.feedbackService.getPage$(0, 200, [], [filter])
			.subscribe(page => this.feedbacks = page.items);
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
