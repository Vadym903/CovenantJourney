import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { circle, divIcon, latLng, LeafletMouseEvent, marker, tileLayer } from "leaflet";

@Component({
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

    @ViewChild("icon") icon: ElementRef | undefined;
    @ViewChild("map") map: ElementRef | undefined;

    options = {
        layers: [
            tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'}),
        ],
        zoom: 5,
        center: latLng(46.879966, -121.726909)
    };

    layers: any = [
        // circle([ 46.95, -122 ], { radius: 5000 }),
        // polygon([[ 46.8, -121.85 ], [ 46.92, -121.92 ], [ 46.87, -121.8 ]]),
        // marker([ 46.879966, -121.726909 ], {icon: new Icon({iconUrl:""})})
    ];

    layer = circle([46.95, -122], {radius: 5000});

    constructor() {
    }

    ngOnInit(): void {

    }

    handleClick(event: LeafletMouseEvent): void {
        const icon = divIcon({
            className: 'custom-div-icon',
            html: this.icon?.nativeElement,
            iconSize: [10, 10],
            iconAnchor: [10, 10]
        });
        this.layers.push(marker([event.latlng.lat, event.latlng.lng], {icon: icon}));

    }

    click() {
        console.log("click works properly")
        this.map?.nativeElement.on('click', (e: any) => {
            console.log(e);
        });
    }

}
