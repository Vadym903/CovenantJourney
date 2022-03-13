import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Page } from '../models/page.model';
import { PagingUtils } from './paging-utils';
import { Filter } from '../models/filter-model';

@Injectable({
    providedIn: 'root'
})
export abstract class AbstractApiService<T> {

    apiPath: string;

    constructor(public http: HttpClient) {
        this.apiPath = environment.serverUrl + this.getURL();
    }

    public abstract getURL(): string;

    create$(entity: T): Observable<T> {
        return this.http.post<T>(this.apiPath, entity);
    }

    getById$(id: number, expandFields?: string): Observable<T> {
        const queryParams = new HttpParams();
        if (expandFields) {
            queryParams.append('expand', expandFields);
        }
        return this.http.get<T>(this.apiPath + `/${id}`, { params: queryParams });
    }

    update$(id: number, entity: T): Observable<T> {
        return this.http.post<T>(this.apiPath + `/${id}`, entity);
    }

    delete$(id: number): Observable<void> {
        return this.http.delete<void>(this.apiPath + `/${id}`);
    }

    getAll$(): Observable<any> {
        return this.http.get(this.apiPath);
    }

    getPage$(page: number, amount: number, sortOptions?: string[], filters?: Filter[], expandFields?: string): Observable<Page<T>> {
        let queryParams = new HttpParams()
            .set('pageNo', page.toString())
            .set('pageSize', amount.toString());

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

        return this.http.get<Page<T>>(this.apiPath, { params: queryParams });
    }
}
