import { Component, Input } from '@angular/core';
import { OwlOptions } from "ngx-owl-carousel-o";
import { Image } from "../../../../_models/image.model";

@Component({
	selector: 'app-image-map-carousel',
	templateUrl: './image-map-carousel.component.html',
	styleUrls: ['./image-map-carousel.component.scss']
})
export class ImageMapCarouselComponent {

	@Input() set images(images: Image[]) {
		this.imageUrls = images.map(image => image.imageFullPath);
	}

	@Input() isNeedToActiveArrows = true;

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
