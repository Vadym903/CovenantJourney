import {
	Component,
	ComponentFactory,
	ComponentFactoryResolver,
	ElementRef,
	OnInit,
	TemplateRef,
	ViewContainerRef
} from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";
import { ApartmentService } from "../../../services/apartment.service";
import { divIcon, latLng, marker, tileLayer } from "leaflet";
import { Apartment } from "../../../_models/apartment.model";
import { CustomMapMarkerComponent } from "./custom-map-marker/custom-map-marker.component";

@Component({
	selector: 'app-all-apartments-map',
	templateUrl: './all-apartments-map.component.html',
	styleUrls: ['./all-apartments-map.component.scss']
})
export class AllApartmentsMapComponent implements OnInit {

	options = {
		layers: [
			tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'}),
		],
		zoom: 5,
		center: latLng(46.879966, -121.726909),
		zoomControl: true
	};

	markers = [];

	constructor(private dialog: MatDialogRef<AllApartmentsMapComponent>,
				private apartmentService: ApartmentService,
				private viewContainerRef: ViewContainerRef
	) {
	}

	ngOnInit(): void {
		this.apartmentService.getPage$()
			.subscribe(page => page.items.forEach(apartment => this.drawMarker(apartment)));
	}

	drawMarker(apartment: Apartment): void {
		const geoData = apartment.geoData;

		const component = this.viewContainerRef.createComponent(CustomMapMarkerComponent);
		component.instance.apartment = apartment;

		const icon = divIcon({
			iconSize: [30, 30],
			html: "<div class='marker'></div>",
			className: 'apartment-marker',
		});

		const newMarker = marker([geoData.latitude, geoData.longitude],
			{
				icon: icon,
				draggable: false,
				autoPanSpeed: 20,
				riseOnHover: true
			}).bindPopup(component.location.nativeElement);

		this.addRisePopupOnHoverEvent(newMarker);
		this.markers.push(newMarker);
	}

	closeMap() {
		this.dialog.close();
	}

	private addRisePopupOnHoverEvent(marker): void {
		marker.on('mouseover', function (e) {
			this.closePopup();
			this.openPopup();
		});

		marker.on('mouseout', function (e) {
			// this.closePopup();
		});
	}

}
