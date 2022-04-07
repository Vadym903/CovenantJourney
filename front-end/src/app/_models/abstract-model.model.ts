export abstract class AbstractModel {
    protected constructor(public id: number) {
    }

    abstract fromObject(object: AbstractModel): AbstractModel;
}
