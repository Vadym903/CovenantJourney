import { Component, Input } from '@angular/core';
import { Image } from "../../../_models/image.model";
import { OwlOptions } from "ngx-owl-carousel-o";

@Component({
	selector: 'app-apartment-info-carousel',
	templateUrl: './apartment-info-carousel.component.html',
	styleUrls: ['./apartment-info-carousel.component.scss']
})
export class ApartmentInfoCarouselComponent {

	@Input() set images(images: Image[]) {
		this.imageUrls = images.map(image => image.imageFullPath);
	}

	imageUrls = [];

	customOptions: OwlOptions = {
		loop: true,
		autoWidth: true,
		autoHeight: true,
		dots: true,
		navSpeed: 700,
		navText: ['', ''],
		items: 1
	}
}
