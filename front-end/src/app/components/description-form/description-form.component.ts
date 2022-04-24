import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { Editor, Toolbar } from "ngx-editor";

@Component({
    selector: 'app-description-form',
    templateUrl: './description-form.component.html',
    styleUrls: ['./description-form.component.scss']
})
export class DescriptionFormComponent implements OnInit {

    @Input() set parentForm(form: FormGroup) {
        if (form) {
            this._form = form;
            this.descriptionControl = form.get('description') as FormControl;
        }
    }
    _form: FormGroup;
    descriptionControl: FormControl;
    editor: Editor;
    isEditorEmpty = true;
    maxLength = 10000;

    toolbar: Toolbar = [
        ["bold", "italic"],
        ["underline", "strike"],
        ["ordered_list", "bullet_list"],
        [{heading: ["h1", "h2", "h3", "h4", "h5", "h6"]}],
        ["link"],
        ["text_color", "background_color"],
        ["align_left", "align_center", "align_right", "align_justify"]
    ];

    constructor() {
    }

    ngOnInit(): void {
        this.editor = new Editor();
    }

}
