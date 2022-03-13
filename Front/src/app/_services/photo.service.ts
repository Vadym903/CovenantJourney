import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  baseUrl = environment.serverUrl + 'photos';
  constructor(private http: HttpClient) { }

  public create$(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('multipartFile', file, file.name);
    return this.http.post(this.baseUrl, formData);
  }
}
