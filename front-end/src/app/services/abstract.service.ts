import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";
import { PagingUtils } from "../_utils/paging-utils";
import { Filter } from "../_models/filter-model";
import { Page } from "../_models/page.model";
import { AbstractModel } from "../_models/abstract-model.model";
import { map } from "rxjs/operators";

@Injectable({providedIn: 'root'})
export abstract class AbstractService<T extends AbstractModel> {

    apiPath: string;

    constructor(public http: HttpClient) {
        this.apiPath = environment.serverUrl + this.getURL();
    }

    public abstract getURL(): string;

    public abstract fromObject(object: T): T;

    create$(entity: T): Observable<T> {
        return this.http.post<T>(this.apiPath, entity)
            .pipe(map(item => this.fromObject(item)));
    }

    getById$(id: number, expandFields?: string): Observable<T> {
        const queryParams = new HttpParams();
        if (expandFields) {
            queryParams.append('expand', expandFields);
        }
        return this.http.get<T>(this.apiPath + `/${id}`, {params: queryParams})
            .pipe(map(item => this.fromObject(item)));
    }

    update$(id: number, entity: T): Observable<T> {
        return this.http.post<T>(this.apiPath + `/${id}`, entity)
            .pipe(map(item => this.fromObject(item)));
    }

    delete$(id: number): Observable<void> {
        return this.http.delete<void>(this.apiPath + `/${id}`);
    }


    getPage$(pageNumber?: number, pageSize?: number, sortOptions?: string[], filters?: Filter[], expandFields?: string): Observable<Page<T>> {
        let queryParams = new HttpParams()

        if (pageSize && pageNumber) {
            queryParams.set('pageNo', pageNumber.toString());
            queryParams.set('pageSize', pageSize.toString());
        }

        if (filters && filters !== undefined) {
            for (const filter of filters) {
                if (filter.key && filter.operation && filter.value) {
                    queryParams = queryParams.append('search', PagingUtils.getFilterValue(filter));
                }
            }
        }

        if (sortOptions && sortOptions !== undefined) {
            for (const sortOption of sortOptions) {
                queryParams = queryParams.append('sort', sortOption);
            }
        }

        if (expandFields && expandFields !== undefined) {
            queryParams = queryParams.append('expand', expandFields);
        }

        return this.http.get<Page<T>>(this.apiPath, {params: queryParams})
            .pipe(map(page => {
                page.items = page.items.map(item => this.fromObject(item));
                return page;
            }));
    }
}
