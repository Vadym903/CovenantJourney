import { environment } from "../../environments/environment";

export class Image {
    constructor(public id?: number,
                public imageName?: string,
                public format?: string,
                public originalName?: string,
                public imageType?: string,
                public imageFullPath?: string
    ) {
    }


    static fromObject(object: Image): Image {
        return new Image(
            object.id,
            object.imageName,
            object.format,
            object.originalName,
            object.imageType,
            environment.serverUrl + "photos/" + object.imageName
        );
    }

}
