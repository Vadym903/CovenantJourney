import { Component, Input } from '@angular/core';
import { divIcon, latLng, LeafletMouseEvent, Map, Marker, marker, tileLayer } from "leaflet";
import { GeoData } from "../../../../_models/geo-data.model";
import { GeoService } from "../../../../services/geo.service";
import { FormGroup } from "@angular/forms";

@Component({
    selector: 'app-apartment-map',
    templateUrl: './apartment-map.component.html',
    styleUrls: ['./apartment-map.component.scss']
})
export class ApartmentMapComponent {

    @Input() form: FormGroup
    leafMap: Map;
    marker: Marker;

    options = {
        layers: [
            tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'}),
        ],
        zoom: 5,
        center: latLng(46.879966, -121.726909),
        zoomControl: true
    };

    constructor(private geoService: GeoService) {
    }

    handleAddressChange(event: LeafletMouseEvent): void {
        const lat = event.latlng.lat;
        const lng = event.latlng.lng;

        this.geoService.getStreetAddress(lat, lng)
            .subscribe(result => this.drawMarker(lat, lng, result));
    }

    drawMarker(lat, lng, streetResult): void {
        const geoData = new GeoData(null, lat, lng, 0, streetResult['display_name']);
        this.form.get('geoData').setValue(geoData);

        const icon = divIcon({
            iconSize: [20, 20],
            html: "<div class='marker'></div>",
            className: 'apartment-marker',
        });

        this.marker = marker([geoData.latitude, geoData.longitude],
            {
                icon: icon,
                draggable: false,
                autoPanSpeed: 20,
                riseOnHover: true
            }).bindPopup('<b>' + geoData.addressName + '</b>')

        this.addRisePopupOnHoverEvent();
    }

    onMapReady(leafMap): void {
        this.leafMap = leafMap;
    }

    zoomChange(event): void {
        // console.log(event);
    }

    private addRisePopupOnHoverEvent(): void {
        this.marker.on('mouseover', function (e) {
            this.openPopup();
        });

        this.marker.on('mouseout', function (e) {
            this.closePopup();
        });
        // this.marker.on("dragend",(e) => {
        //     console.log(e);
        //     const lat = e.target._latlng.lat;
        //     const lng = e.target._latlng.lng;
        //     this.geoService.getStreetAddress(lat, lng)
        //         .subscribe(result => this.drawMarker(lat, lng, result));
        // });
    }

}
